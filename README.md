# JAVA文字识别

#### 介绍
JAVA文字识别

#### 软件架构
使用JAVA的窗口截图工具，后端调用的是百度云OCR文字识别接口。

#### 安装教程

1.  百度云上面申请自己的百度云OCR产品，将密钥等信息填入ocr-common的配置文件（APIConfigConstant.java）中
2.  mvn clean install package
3.  java -jar ocr-gui-1.0.0.jar

#### 使用说明

1.  点击截图
2.  选取区域
3.  识别结果自动写入剪切板

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 码云特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5.  码云官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
