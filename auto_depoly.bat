:: 执行集成测试，确认测试没有问题全部通过
:: mvn clean  test
:: 请确认请求域名已经替换回  【api.polyv.net】


:: @echo off
chcp 65001
echo "请确认请求域名已经替换回  【api.polyv.net】 "
pause
:: 调用示例  auto_deploy.bat  1.0.11
git checkout dev
:: #同步开发分支所有代码
git pull
:: git commit -am "JSDK-1 auto_deploy"  &&  git  push
:: # 切换到master分支
git checkout master
PAUSE
:: 同步主分支代码
git pull origin master
:: # 合并dev 分支代码
git merge dev --no-ff
:: 查看同步状态，确认合并完成
git status
PAUSE
:: 针对当前分支新建一个版本tag
git  tag  %1
:: # 提交master分支到origin远程仓库
git push origin master  --tags
:: # 提交master分支到github远程仓库
git push github master
:: # 提交master分支到gitee远程仓库
git push gitee master
:: # 切换回dev分支
git checkout dev
PAUSE



:: 剩余工作如下
:: # 部署到中央版本库
:: mvn clean install deploy -Dmaven.test.skip=true -P release
:: 登录码云重启文档服务，使最新文档生效，并人工确认
:: 登录 maven 私服更新到中央版本库 ， https://oss.sonatype.org/#stagingRepositories ，并人工确认
:: 请确认从中央仓库下载的jar包请求域名已经替换回  【api.polyv.net】