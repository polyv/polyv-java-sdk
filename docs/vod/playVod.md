## 目标

&emsp;&emsp;本文档将在10分钟内快速完成一个视频点播的完整业务流程，前提是已经完成[系统的初始化过程](/quick_start?id=_2初始化系统)。



## 点播业务流程



#### 点播业务介绍


<div id="player"></div>

<script>
  new Vue({
    el: '#player',
    data() {
    return {
      vodPlayerJs: 'https://player.polyv.net/script/player.js',
      vid:'88083abbf5d3d0d1bb3d7f04e231b8d8_8',
    };
  },
  mounted(){
      this.loadPlayerScript(this.loadPlayer);
  },
  methods: {
    loadPlayerScript(callback) {
      if (!window.polyvPlayer) {
        const myScript = document.createElement('script');
        myScript.setAttribute('src', this.vodPlayerJs);
        myScript.onload = callback;
        document.body.appendChild(myScript);
      } else {
        callback();
      }
    },
    loadPlayer() {
      const polyvPlayer = window.polyvPlayer;
      this.player = polyvPlayer({
        wrap: '#player',
        vid: this.vid ,
        autoplay:false
      });
    }
  },
  destroyed() {
    if (this.player) {
        this.player.destroy();
    }
  }
  })
</script>
  

#### 流程

  此场景适合于通用的点播视频发布流程，包括视频上传，属性设置，发布观看三个环节。 

![image-20210113155549477](../img/image-20210113155549477.png)



####  代码示例

```java
   /**
     * 快速创建三分屏频道，适用于直播教学场景
     * @throws IOException IO异常
     * @throws NoSuchAlgorithmException 系统异常
     */
//    @Test
    public void testQuickCreatePPT() throws IOException, NoSuchAlgorithmException {
        QuickCreatePPTChannelRequest quickCreatePPTChannelRequest = new QuickCreatePPTChannelRequest();
        QuickCreateChannelResponse quickCreateChannelResponse;
        String path = LiveChannelQuickCreatorTest.class.getResource("/file/PPT.pptx").getPath();
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.DAY_OF_MONTH, instance.get(Calendar.DAY_OF_MONTH) + 1);
        //创建频道
        String requestId = LiveSignUtil.generateUUID();
        //频道相关基础设置-频道名
        quickCreatePPTChannelRequest.setName("直播教学场景演示")
                //频道相关基础设置-频道密码
                .setChannelPasswd(getRandomString(6))
                //频道相关基础设置-连麦人数
                .setLinkMicLimit(5)
                //频道相关基础设置-主持人名称
                .setPublisher("thomas教授")
                //频道相关基础设置-是否无延迟
                .setPureRtcEnabled(LiveConstant.Flag.YES.getFlag())
                //频道相关基础设置-开播时间
                .setStartTime(instance.getTime().getTime())
                //==========================================
                //频道初始化设置-频道图标地址
                .setCoverImg("https://wwwimg.polyv.net/assets/dist/images/v2020/page-home/brand-advantage/row-2-3.svg")
                //频道初始化设置-引导图地址
                .setSplashImg(
                        "https://wwwimg.polyv.net/assets/dist/images/v2020/news-info-md/product-dynamic-bg_v3.jpg")
                //频道初始化设置-频道描述
                .setDesc("POLYV保利威是广州易方信息科技股份有限公司旗下拥有自主知识产权的视频云计算服务平台，其中包含 云点播 、云直播 " +
                        "和其它视频服务，提供API、SDK技术支持，并拥有国家专利级别的PlaySafe®视频版权保护技术及三套CDN加速，致力为用户提供稳定、安全、快速的企业级视频云服务。")
                //频道初始化设置-设置暖场图
                .setCoverImage("https://s1.videocc.net/live-watch/assets/img/default-splash-img.07657078.jpg")
                //频道初始化设置-点击暖场图跳转的地址
                .setCoverHref("http://www.baidu.com")
                //频道初始化设置-设置暖场视频
//              .setWarmUpFlv("http://www.w3school.com.cn/example/html5/mov_bbb.mp4")
                //==========================================
                //聊天室讲师信息-昵称
                .setNickname("thomas-gogo")
                //聊天室讲师信息-讲师头衔
                .setActor("刘老师")
                //聊天室讲师信息-讲师头像
                .setAvatar(
                        "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2069606413,3553249962&fm=26&gp=0" +
                                ".jpg")
                //==========================================
                // 讲课文档设置-讲课文档，当前支持DOC、PPT、PDF
                .setFile(new File(path))
                //讲课PPT设置-转换类型（‘common’：转普通图片， ‘animate’：转动画效果）
                .setType("common")
//                //讲课PPT设置-文档转换完成后的回调地址，不需要不传
//                .setCallbackUrl("http://www.baidu.com/callback")
                //讲课PPT设置-文档名称
                .setDocName("直播教学课件");
        
        quickCreateChannelResponse = new LiveChannelQuickCreatorServiceImpl().quickCreatePPTSence(quickCreatePPTChannelRequest);
        Assert.assertNotNull(quickCreateChannelResponse);
        log.debug("快速创建三分屏频道成功，{}", JSON.toJSONString(quickCreateChannelResponse));
        log.debug("网页开播地址：https://live.polyv.net/web-start/login?channelId={}  , 登录密码： {}",
                quickCreateChannelResponse.getLiveChannelBasicInfoResponse().getChannelId(),
                quickCreatePPTChannelRequest.getChannelPasswd());
        log.debug("网页观看地址：https://live.polyv.cn/watch/{} ",
                quickCreateChannelResponse.getLiveChannelBasicInfoResponse().getChannelId());
    
    
        /**
         * todo : B端客户的业务逻辑，将quickCreateChannelResponse的相关信息保持到自己的DB中组织业务逻辑
         */
    
        /**
         * todo : 采用网页开播或者客户端开播，直播结束后 ，可以拉取用户观看直播的观看数据，对观看效果做进一步的分析，改进直播流程和细节
         */
        //打印观看日志
        printViewLog(quickCreateChannelResponse.getLiveChannelBasicInfoResponse().getChannelId(), requestId);
    }

  
    
    /**
     * 打印频道观看日志
     * @param channelId
     * @param requestId
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    private void printViewLog(String channelId, String requestId) throws IOException, NoSuchAlgorithmException {
        LiveListChannelViewlogRequest liveListChannelViewlogRequest = new LiveListChannelViewlogRequest();
        LiveListChannelViewlogResponse liveListChannelViewlogResponse;
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.DAY_OF_MONTH, instance.get(Calendar.DAY_OF_MONTH) + 2);
        //依据频道号和起止时间查询观看日志
        liveListChannelViewlogRequest.setChannelId(channelId)
                .setStartTime(new Date())
                .setEndTime(instance.getTime());
        liveListChannelViewlogResponse = new LiveChannelViewdataServiceImpl().listChannelViewlog(
                liveListChannelViewlogRequest);
        Assert.assertNotNull(liveListChannelViewlogResponse);
        if (liveListChannelViewlogResponse != null) {
            //to do something ......
            log.debug("测试分页查询频道观看日志成功，{}", JSON.toJSONString(liveListChannelViewlogResponse));
        }
    }

```

#### 请求描述


| 参数名 | 必选 | 类型 | 说明 |
| -- | -- | -- | -- |
| name | true | String | 自定义频道名称，一般是课程主题、会议主题、培训主题等，例如 财务制度培训、乌镇峰会 |
| channelPasswd | true | String | 自定义频道密码，B端讲师通过该密码进入直播间开播，长度不能超过16位,必须同时包含字母和数字 |
| linkMicLimit | false | Integer | 连麦人数，-1=<取值范围<=账号级的连麦人数，-1：表示使用账号默认的连麦人数，最大16人（注：账号级连麦人数需通知平台管理员设置才生效） |
| publisher | false | String | 主持人名称 |
| pureRtcEnabled | false | String | 是否为无延时直播，Y 表示开启，默认为N |
| coverImg | false | String | 频道图标地址 |
| splashImg | false | String | 引导图地址 |
| startTime | false | Long | 直播开始时间，13位时间戳，设置为0 表示关闭直播开始时间显示 |
| desc | false | String | 直播介绍 |
| nickname | true | String | 讲师昵称 |
| actor | true | String | 讲师头衔 |
| avatar | false | String | 头像图片地址 |
| coverImage | false | String | 暖场图片地址，图片大小建议：800x450，支持PNG、JPEG、GIF格式 |
| coverHref | false | String | 点击暖场图片后浏览器跳转地址 |
| warmUpFlv | false | String | 暖场视频地址(http地址)，移动端不支持FLV视频文件，建议使用MP4视频文件 |
| file | true | File | 上传的文件不超过50M，格式限制为（ppt， pdf，pptx，doc，docx，wps, xls，xlsx） |
| type | false | String | 转换类型（‘common’：转普通图片， ‘animate’：转动画效果）默认不传转普通，因为只有ppt，pptx可以转动画，其他类型文件会自动转成普通；文件转动画转失败会直接把类型转为普通 |
| docName | false | String | 文档名称（不传默认使用ppt上传的文件获取到的文件名作为文档名称，文档名称不得超过100个字符） |
| callbackUrl | false | String | 文档上传转换成功回调地址 |
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 |


#### 返回描述


| 参数名 | 类型 | 说明 |
| -- | -- | -- |
| liveChannelBasicInfoResponse | LiveChannelBasicInfoResponse | 频道信息【详见[LiveChannelBasicInfoResponse参数描述](playVod.md?id=polyv41)】 |
| sonChannelInfos | Array | 子频道信息【详见[LiveSonChannelInfoResponse参数描述](playVod.md?id=polyv42)】 |

<h6 id="polyv41"><a href="#/playLive.md?id=polyv41"data-id="LiveChannelBasicInfoResponse参数描述"class="anchor"><span>LiveChannelBasicInfoResponse参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 |
| -- | -- | -- |
| channelId | String | 频道号 |
| name | String | 频道名称 |
| channelPasswd | String | 频道密码 |
| publisher | String | 主持人名称 |
| startTime | Date | 直播开始时间，关闭时为null |
| pageView | Integer | 页面累计观看数 |
| likes | Integer | 观看页点赞数 |
| coverImg | String | 频道图标url |
| splashImg | String | 频道引导图url |
| splashEnabled | String | 引导页开关（取值为Y/N） |
| desc | String | 直播介绍 |
| consultingMenuEnabled | String | 咨询提问开关（取值为Y/N） |
| maxViewerRestrict | String | 限制最大在线观看人数开关（取值为Y/N） |
| maxViewer | Integer | 最大在线观看人数 |
| watchStatus | String | 频道的观看页状态，取值为：live（直播中）、end（直播结束）、playback（回放中）、waiting（等待直播） |
| watchStatusText | String | 观看页状态描述，直播中，回放中，已结束，未开始 |
| userCategory | UserCategory | 频道所属分类的信息【详见[UserCategory参数描述](playVod.md?id=polyv43)】 |
| authSettings | Array | 直播观看条件列表【详见[AuthSetting参数描述](playVod.md?id=polyv44)】 |

<h6 id="polyv42"><a href="#/playLive.md?id=polyv42"data-id="LiveSonChannelInfoResponse参数描述"class="anchor"><span>LiveSonChannelInfoResponse参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 |
| -- | -- | -- |
| account | String | 子频道号 |
| userId | String | POLYV用户ID，和保利威官网一致，获取路径：官网->登录->直播（开发设置） |
| channelId | String | 频道号 |
| passwd | String | 子频道密码 |
| nickname | String | 子频道名称 |
| stream | String | 子频道流名（单独使用无效） |
| status | String | 子频道状态 |
| createdTime | Date | 创建子频道时间 |
| lastModified | Date | 子频道最后修改时间 |
| sort | Integer | 频道中所有子频道序号 |
| avatar | String | 子频道头像 |
| pageTurnEnabled | String | 子频道翻页权限（只能一个子频道有） |
| notifyEnabled | String | 发布公告权限(Y/N) |
| checkinEnabled | String | 开启签到权限(Y/N) |
| voteEnabled | String | 发起投票(Y/N) |
| role | String | 子频道角色 |
| pushUrl | String | 子频道推流地址（子频道推流请参考后台导播台使用） |

<h6 id="polyv43"><a href="#/playLive.md?id=polyv43"data-id="UserCategory参数描述"class="anchor"><span>UserCategory参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 |
| -- | -- | -- |
| categoryId | Integer | 分类ID |
| categoryName | String | 分类名称 |
| userId | String | POLYV用户ID，和保利威官网一致，获取路径：官网->登录->直播（开发设置） |
| rank | Integer | 分类的排序值 |

<h6 id="polyv44"><a href="#/playLive.md?id=polyv44"data-id="AuthSetting参数描述"class="anchor"><span>AuthSetting参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 |
| -- | -- | -- |
| channelId | String | 频道号 |
| userId | String | POLYV用户ID，和保利威官网一致，获取路径：官网->登录->直播（开发设置） |
| rank | Integer | 用于实现一个频道设置两个观看条件，为1或2（1为主要条件，2为次要条件） |
| globalSettingEnabled | String | 是否开启全局设置（Y/N） |
| enabled | String | 是否开启观看条件(Y/N) |
| authType | String | 观看条件类型(1. 无限制 none 2. 验证码观看 code 3. 付费观看 pay 4. 白名单观看 phone 5. 登记观看 info 6. 分享观看 wxshare 7. 自定义授权观看 custom 8. 外部授权观看 external) |
| authTips | String | 白名单观看提示信息 |
| payAuthTips | String | 付费观看提示信息 |
| authCode | String | 验证码观看方式的验证码 |
| qcodeTips | String | 验证码观看方式的二维码提示 |
| qcodeImg | String | 验证码观看方式的二维码图片 |
| price | Integer | 付费观看的价格 |
| watchEndTime | Date | 付费观看，截止时间，为null表示：一次付费，永久有效 |
| validTimePeriod | Integer | 付费观看的截止时长（天） |
| customKey | String | 自定义授权观看的key |
| customUri | String | 自定义授权观看的接口地址 |
| externalKey | String | 外部授权观看的key |
| externalUri | String | 外部授权观看的接口地址 |
| externalRedirectUri | String | 外部授权观看，用户直接访问观看页时的跳转地址 |



 