#!/usr/bin/env bash
echo "测试环境发布脚本"
set -e
home=$(pwd)
path="/opt/apps/test/logistics-scaffold"   #复制路径
package=$1 #压缩包
packageSuffix=${2:-bak}
if [[ -e "${package}" ]];then :
    echo "文件存在"
	
	echo "使用管理员权限解压"
	cd "${path}"  || { echo "当前路径不存在！"; exit 1; }
	
	
	if [[ -e "${package}" ]];then 
		cp -b "${package}" "${package}.${packageSuffix}" || { echo "原文件备份失败!"; exit 1; }
	fi
    
	
	mv -b "${home}/${package}" "${package}"
	if [[ $? == 0 ]];then
		echo "移动并备份成功!"
	else
		echo "移动并备份失败!"
		exit 1
	fi

   
    tar -xvf  "${package}"
	
    project=${package:0:(${#package}-7)}
	cp  server_heap.sh  "${project}/bin"

    echo "项目授权:${project}给fkhservice"
    chown -R fkhservice:fkhwww -- * 

    echo "启动项目:${project}"
	cd "${project}"  || { echo "当前路径不存在！"; exit 1; }
	
    echo "切换fkhservice用户运行"
    su fkhservice  -c "./bin/server_heap.sh -r test -t"
    
    
else
echo "文件不存在"
fi




