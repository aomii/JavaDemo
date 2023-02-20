package com.aoming.fkh.service.impl;

import com.aoming.App;
import com.aoming.fkh.service.ClassVoService;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description:  </p>
 *
 * @author aoming
 * @version x.x.x
 * @email "mailto:aoming@fkhwl.com"
 * @date 2023.02.02 15:23
 * @since x.x.x
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {App.class})
public class ClassVoServiceImplTest extends TestCase {

    @Autowired
    private ClassVoService classVoService;

    @Test
    public void testCompressData() {
        classVoService.compressData();
    }
    @Test
    public void testGetHistoryLocation() {
        classVoService.getHistoryLocation(1L);
    }
}