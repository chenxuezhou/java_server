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

