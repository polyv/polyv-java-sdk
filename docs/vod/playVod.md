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

  此场景适合于通用的点播视频发布流程，包括视频上传，视频管理，发布观看，获取日志四个环节。 

![image-20210414110739168](../img/image-20210414110739168.png)



####  代码示例

[视频上传]()

[视频管理]()

[发布管理-通过微信观看]()

[发布管理-HTML代码]()

[获取日志]() 