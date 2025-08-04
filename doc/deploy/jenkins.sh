
#!/bin/bash
set -e  # 任何命令失败时立即退出

# 清理旧备份并部署新包
echo "正在清理旧备份..."
sudo rm -f /opt/apps/test/fkh-network-freight/fkh-network-freight.tar.gz.back
sudo mv /opt/apps/test/fkh-network-freight/fkh-network-freight.tar.gz /opt/apps/test/fkh-network-freight/fkh-network-freight.tar.gz.back || {
    echo "备份旧包失败，可能文件不存在，继续执行..."
}

echo "移动新包至部署目录..."
sudo mv /home/publisher/fkh-network-freight.tar.gz /opt/apps/test/fkh-network-freight/ || {
    echo "新包不存在，部署终止！"
    exit 1
}

echo "修正文件权限..."
sudo chown -R fkhservice:fkhwww /opt/apps/test/fkh-network-freight/*

# 切换用户解压并启动服务
echo "解压并启动服务..."
sudo -u fkhservice bash -c "\
  cd /opt/apps/test/fkh-network-freight && \
  tar -zxvf fkh-network-freight.tar.gz --strip-components=1 && \
  ./bin/server.sh -r test"