## 直播SDK更新日志

| 版本号 | 更新日期 | 更新历史 | 升级注意事项 |
| -- | -- | -- |  -- |
| 1.1.0 | 2021-04-19 | 1. 优化部分bug | 无 |
| 1.0.25 | 2021-04-15 | 1. 修复部分字段类型与api不一致的问题；<br /> 2. 优化直播文档； | [查询频道基本信息](/channelOperate?id=_5、查询频道基本信息)返回对象AuthSetting参数price字段类型修改为Float<br/>[查询频道问卷结果](/questionnaireService?id=_3、查询频道问卷结果)返回对象Answer参数score字段类型修改为Integer<br/>[分页查询问卷结果](/questionnaireService?id=_4、分页查询问卷结果)返回对象Answer参数score字段类型修改为Integer<br/>[获取频道单场抽奖的中奖记录](/lotteryService?id=_2、获取频道单场抽奖的中奖记录)返回对象LotteryWinnerDetail参数ext字段类型修改为String<br/>[查询历史聊天信息](/chatRoomService?id=_3、查询历史聊天信息)roomId字段类型修改为String<br/>[查询频道踢人列表](/chatRoomService?id=_6、查询频道踢人列表)roomId字段类型修改为String |
| 1.0.24 | 2021-04-14 | 1. 对文档必填项进行检查核对；<br /> 2. 全局参数requestId统一由系统生成，用户无需设置；<br /> 3. 对代码非空检查进行全局优化；<br /> 4. 对输入参数为文件做全局非空校验；<br /> 5. 对于返回文档的接口，优化错误返回处理； |  无 |