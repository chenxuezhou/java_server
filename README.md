
```
#### 项目结构
```
# 项目模块如下
- eladmin-common 公共模块
    - aop.limit 接口限流自定义注解
    - exception 项目统一异常的处理
    - mapper mapstruct的通用mapper
    - redis redis缓存相关配置
    - swagger2 接口文档配置
    - utils 通用工具
- eladmin-system 系统核心模块
	- config 配置跨域与静态资源
	- modules 系统相关模块
		- monitor 系统监控
		    - config 配置日志拦截器与WebSocket等
		    - domain 实体类
		    - repository 数据库操作
		    - rest 前端控制器
		    - service 业务接口
		        - impl 业务接口实现
		        - query 业务查询
        - quartz 定时任务
        - security 系统安全
	        - config  JWT的安全过滤器配置
		    - rest 用户登录授权的接口
		    - security 配置spring security
		    - service 用户登录与权限的处理
		    - utils JWT工具
    	- system 系统管理
- eladmin-logging 系统日志模块
- eladmin-tools 系统第三方工具模块
- eladmin-generator 系统代码生成模块
```

{
	date = 2019 - 04 - 13, package = me.zhengjie.modules.test, author = jie, columns = [{
		columnType = Long,
		columnShow = false,
		isNullable = NO,
		changeColumnName = id,
		columnComment = ID,
		columnKey = PRI,
		columnName = id,
		capitalColumnName = Id
	}, {
		columnType = String,
		columnShow = true,
		isNullable = YES,
		changeColumnName = avatar,
		columnComment = 头像地址,
		columnKey = ,
		columnName = avatar,
		capitalColumnName = Avatar
	}, {
		columnType = Timestamp,
		columnShow = true,
		isNullable = YES,
		changeColumnName = createTime,
		columnComment = 创建日期,
		columnKey = ,
		columnName = create_time,
		capitalColumnName = CreateTime
	}, {
		columnType = String,
		columnShow = true,
		isNullable = YES,
		changeColumnName = email,
		columnComment = 邮箱,
		columnQuery = 1,
		columnKey = UNI,
		columnName = email,
		capitalColumnName = Email
	}, {
		columnType = Long,
		columnShow = true,
		isNullable = YES,
		changeColumnName = enabled,
		columnComment = 状态： 1 启用、 0 禁用,
		columnKey = ,
		columnName = enabled,
		capitalColumnName = Enabled
	}, {
		columnType = String,
		columnShow = true,
		isNullable = YES,
		changeColumnName = password,
		columnComment = 密码,
		columnKey = ,
		columnName = password,
		capitalColumnName = Password
	}, {
		columnType = String,
		columnShow = true,
		isNullable = YES,
		changeColumnName = username,
		columnComment = 用户名,
		columnKey = UNI,
		columnName = username,
		capitalColumnName = Username
	}, {
		columnType = Timestamp,
		columnShow = true,
		isNullable = YES,
		changeColumnName = lastPasswordResetTime,
		columnComment = 最后修改密码的日期,
		columnKey = ,
		columnName = last_password_reset_time,
		capitalColumnName = LastPasswordResetTime
	}], moduleName = eladmin - system, pkColumnType = Long, className = User, hasBigDecimal = false, hasQuery = true, tableName = user, hasTimestamp = true, queryColumns = [{
		columnType = String,
		columnShow = true,
		isNullable = YES,
		changeColumnName = email,
		columnComment = 邮箱,
		columnQuery = 1,
		columnKey = UNI,
		columnName = email,
		capitalColumnName = Email
	}], changeClassName = user
}

