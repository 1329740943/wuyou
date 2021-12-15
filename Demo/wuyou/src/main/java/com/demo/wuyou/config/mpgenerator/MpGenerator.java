package com.demo.wuyou.config.mpgenerator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 逆向工程
 */
public class MpGenerator {
    /**
     * 逆向先引入：mybatisPlus包、逆向工程包、freemarker包
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        //outputDir：生成文件的输出目录，默认值：D 盘根目录
        gc.setOutputDir(projectPath + "/src/main/java");
        //IdType设置主键生成策略，默认值null
        gc.setIdType(IdType.UUID);
        //Author设置作者
        gc.setAuthor("CJF");
        // XML文件中的通用结果映射ResultMap
        gc.setBaseResultMap(true);
        // XML通用的columList
        gc.setBaseColumnList(true);
        //Open：是否打开输出目录
        gc.setOpen(false);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/wuyu?serverTimezone=UTC&amp&characterEncoding=utf-8&useSSL=false");

        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        //设置数据库类型，默认MYSQL
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        //   输入生成逆向文件的目录名
        pc.setModuleName(scanner("模块名"));
        //父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
        pc.setParent("com.demo.wuyou");
        mpg.setPackageInfo(pc);

        //模板配置


        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/src/main/resources/mapping/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setTemplate(new TemplateConfig().setXml(null));


        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        //设置命名格式
        strategy.setNaming(NamingStrategy.underline_to_camel);
        //数据库表字段映射到实体的命名策略, 未指定按照 naming 执行
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //【实体】是否为lombok模型（默认 false）
        strategy.setEntityLombokModel(true);
        //生成 @RestController 控制器
        strategy.setRestControllerStyle(true);
        strategy.setInclude(scanner("表名").split(","));
        //驼峰转连字符
        strategy.setControllerMappingHyphenStyle(true);
        //表名前缀
        strategy.setTablePrefix(pc.getModuleName() + "_");
        //是否生成实体时，生成字段注解
        strategy.entityTableFieldAnnotationEnable(true);
        //设置自定义继承的Entity类全称，带包名
//       strategy.setSuperEntityClass("com.lj.common.BaseEntit");
        //设置自定义继承的Controller类全称，带包名
//        strategy.setSuperControllerClass("com.lj.common.BaseController");
        //设置自定义基础的Entity类，公共字段
//        strategy.setSuperEntityColumns("id");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}
