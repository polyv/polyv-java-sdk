:: @echo off

git checkout dev
:: #提交所有代码
git pull 
git commit -am "JSDK-1 auto_deploy"  &&  git  push
:: # 切换到master分支
git checkout master
git pull origin master
:: # 合并dev 分支代码
git merge dev --no-ff
git status
:: # 提交master分支到origin远程仓库
git push origin master
PAUSE
:: # 部署到中央版本库
:: mvn clean install deploy -Dmaven.test.skip=true -P release
:: # 提交master分支到github远程仓库
git push github master
:: # 提交master分支到gitee远程仓库
git push gitee master
:: # 切换回dev分支
git checkout dev
PAUSE
