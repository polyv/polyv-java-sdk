:: @echo off

git checkout V1.0.0
:: #提交所有代码
git pull 
git commit -am "JSDK-1 自动脚本提交(版本升级、bug优化)"  &&  git  push
:: # 切换到master分支
git checkout master

git pull origin master

:: # 合并V1.0.0 分支代码
git merge V1.0.0
git status
:: # 提交master分支到origin远程仓库
git push origin master
:: # 部署到中央版本库
:: mvn clean install deploy -Dmaven.test.skip=true -P release
PAUSE
:: # 提交master分支到github远程仓库
git push github master
:: # 切换回V1.0.0分支
git checkout V1.0.0

PAUSE
