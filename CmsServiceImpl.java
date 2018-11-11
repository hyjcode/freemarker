package cn.itcast.core.service;

import cn.itcast.core.dao.good.GoodsDao;
import cn.itcast.core.dao.good.GoodsDescDao;
import cn.itcast.core.dao.item.ItemCatDao;
import cn.itcast.core.dao.item.ItemDao;
import cn.itcast.core.pojo.good.Goods;
import cn.itcast.core.pojo.good.GoodsDesc;
import cn.itcast.core.pojo.item.Item;
import cn.itcast.core.pojo.item.ItemCat;
import cn.itcast.core.pojo.item.ItemQuery;
import com.alibaba.dubbo.config.annotation.Service;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import javax.servlet.ServletContext;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CmsServiceImpl implements  CmsService, ServletContextAware {
    
    
    @Autowired
    private FreeMarkerConfig freemarkerConfig;

    private Configuration configuration;

   
    private ServletContext servletContext;

        
        
    

    @Override
    public void createPage() throws Exception {
		Map<String, Object> rootMap = new HashMap<>();

        //获取freemarker初始化对象
        configuration = freemarkerConfig.getConfiguration();
        //加载模板对象
        Template template = configuration.getTemplate("item.ftl");

        //定义输出流
        Writer out = new OutputStreamWriter(new FileOutputStream(new File("123.html")), "utf-8");
        //生成静态化页面
        template.process(rootMap, out);
        //关闭流
        out.close();

    }

    /**
     * 传入相对路径, 根据相对路径自动转换成绝对路径
     * @param path
     * @return
     */
    private String getRealPath(String path) {
        String realPath = servletContext.getRealPath(path);
        System.out.println("=======realPath========" + realPath);
        return realPath;
    }

    /**
     * 使用spring实例化的ServletContext对象给我们自定义的全局的ServletContext对象赋值, 也即是对ServletContext实例化
     * @param servletContext
     */
    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
