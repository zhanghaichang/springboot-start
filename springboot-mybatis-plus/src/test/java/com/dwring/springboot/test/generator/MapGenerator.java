package com.qf.springboot.test.generator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @Title: MapGenerator.java
 * @Package com.qf.springboot.test
 * @Description: 代码生成器
 * @author haichangzhang
 * @date 2017年7月11日 上午11:38:44
 * @version V1.0
 */
public class MapGenerator {

	/**
	 * <p>
	 * MySQL 生成演示
	 * </p>
	 */
	public static void main(String[] args) {
		// 代码生成器
		AutoGenerator mpg = new AutoGenerator()
				.setGlobalConfig(
						// 全局配置
						new GlobalConfig().setOutputDir("/develop/code/")// 输出目录
								.setFileOverride(true)// 是否覆盖文件
								.setActiveRecord(true)// 开启 activeRecord 模式
								.setEnableCache(false)// XML 二级缓存
								.setBaseResultMap(true)// XML ResultMap
								.setBaseColumnList(true)// XML columList
								.setAuthor("Zhanghaichang"))
				.setDataSource(
						// 数据源配置
						new DataSourceConfig().setDbType(DbType.MYSQL)// 数据库类型
								.setTypeConvert(new MySqlTypeConvert() {
									// 自定义数据库表字段类型转换【可选】
									@Override
									public DbColumnType processTypeConvert(String fieldType) {
										System.out.println("转换类型：" + fieldType);
										return super.processTypeConvert(fieldType);
									}
								}).setDriverName("com.mysql.jdbc.Driver").setUsername("root").setPassword("root")
								.setUrl("jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf8"))
				.setStrategy(
						// 策略配置
						new StrategyConfig().setTablePrefix(new String[] { "bmd_", "mp_" })// 此处可以修改为您的表前缀
								.setNaming(NamingStrategy.underline_to_camel)// 表名生成策略
				// .setInclude(new String[] { "user" })需要生成的表
				).setPackageInfo(
						// 包配置
						new PackageConfig().setModuleName("test").setParent("com.qf.springboot")// 自定义包路径
								.setController("controller")// 这里是控制器包名，默认 web
				).setCfg(
						// 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
						new InjectionConfig() {
							@Override
							public void initMap() {
								Map<String, Object> map = new HashMap<>();
								map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
								this.setMap(map);
							}
						}.setFileOutConfigList(
								Collections.<FileOutConfig>singletonList(new FileOutConfig("/templates/mapper.xml.vm") {
									// 自定义输出文件目录
									@Override
									public String outputFile(TableInfo tableInfo) {
										return "/develop/code/xml/" + tableInfo.getEntityName() + ".xml";
									}
								})))
				.setTemplate(
						// 关闭默认 xml 生成，调整生成 至 根目录
						new TemplateConfig().setXml(null));

		// 执行生成
		mpg.execute();

		// 打印注入设置，这里演示模板里面怎么获取注入内容【可无】
		System.err.println(mpg.getCfg().getMap().get("abc"));
	}
}
