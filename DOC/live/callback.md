# 直播回调说明

## 1.直播状态回调
### 作用
账号下频道直播状态改变后，会对所设置的接口地址进行回调通知。

### 注意事项

该回调不支持重试机制

### 描述

设置接口地址后，如果账号下频道有进行推流、断流操作导致频道直播状态改变，直播系统会将以下参数channelId（频道号）和status（直播状态：live表示开始直播，end表示直播结束）以GET方式提交到给用户自定义的回调接口进行通知，如：http://abc.com/test.do?channelId=123456&status=live&timestamp=1557976774000&sign=xxdxxxxx&sessionId=xxxxxddd&startTime=1557976777111&endTime=1557976777111

| 参数名    | 类型   | 说明                                                         |
| --------- | ------ | ------------------------------------------------------------ |
| channelId | int    | 频道ID                                                       |
| status    | string | 直播频道的状态：live正在直播，end直播结束                    |
| timestamp | long   | 13位的时间戳                                                 |
| sign      | String | 校验的加密字符串，生成的规则md5(AppSecret+timestamp)，AppSecret是直播系统的用密匙 |
| sessionId | string | 直播的场次ID                                                 |
| startTime | long   | 直播的开始时间,13位的时间戳                                  |
| endTime   | long   | 直播的结束时间(当status=end的时候有值，status=live的时候为空值),13位的时间戳 |

### 如何设置？

方法1：通过后台设置

在直播后台中，点击【开发设置】，然后点击【回调设置】，在此进行设置

![image-20201021101815391](C:\project\IdeaProject\polyv-java-sdk\DOC\live\img\image-20201021101815391.png)

方法2：通过接口设置

[设置直播状态回调通知url](  )

## 2.转存成功回调

### 作用

账号下直播暂存文件成功转存至点播后，会对所设置的接口地址进行回调通知。

### 注意事项

该回调不支持重试机制

### 描述

设置接口地址后，如果账号有进行暂存视频转存，即将直播生成的录制文件转存到点播中，如果转存视频处理完毕为已完成状态，直播系统会将以下参数channelId(频道号)、vid(转存成功的视频ID)、title(视频标题)、duration(视频时长)和fileSize（视频文件大小）以GET方式提交到给用户自定义的回调接口进行通知，如：http://abc.com/test.do?channelId=123456&vid=e6b23c6f5134943a015bc117e2854eae_e&title=视频标题&duration=01:23:45&fileSize=123400&timestamp=1557976774000&sign=xxxxxxxxxx&fileId=359a81ed8fd8cb83d88ddcd97d9e8a2b&videoId=b1c6f3ad2c&origin=auto&sessionIds=["20190703145126,4,fdqbopvtnv","20190703145126,8,fdqbopvtnv"]

| 参数名     | 类型   | 说明                                                         |
| ---------- | ------ | ------------------------------------------------------------ |
| channelId  | int    | 频道号ID                                                     |
| vid        | string | 转存成功的视频ID                                             |
| title      | string | 视频标题                                                     |
| duration   | string | 视频时长 格式为 hh:mm:ss                                     |
| fileSize   | long   | 视频文件大小，单位为byte                                     |
| timestamp  | long   | 13位的时间戳                                                 |
| sign       | String | 校验的加密字符串，生成的规则md5(AppSecret+timestamp)，AppSecret是直播系统的用密匙 |
| sessionIds | String | 录制的场次和时间对应的数组字符串，格式：["20190703145126,4,fdqbopvtnv","20190703145126,8,fdqbopvtnv"] ，其中："20190703145126,4,fdqbopvtnv"  第一个字段是开始时间，第二个字段是直播的时长，第三个是对应的sessionId。 |
| fileId     | string | 转存对应的录制文件id                                         |
| videoId    | string | 转存回放唯一的id                                             |
| origin     | string | 转存的录制来源。manual-云录制，auto-自动录制，merge-合并，clip-裁剪 |
| sessionId  | string | 回放对应的单个场次id                                         |
| userId     | string | 用户id                                                       |
| status     | string | 转存成功返回success                                          |

### 如何设置？

方法1：通过后台设置

在直播后台中，点击【开发设置】，然后点击【回调设置】，在此进行设置

![image-20201021101815391](C:\project\IdeaProject\polyv-java-sdk\DOC\live\img\image-20201021101815391.png)

方法2：通过接口设置

[设置转存成功回调通知url](  )