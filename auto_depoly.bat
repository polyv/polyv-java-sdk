:: @echo off

git checkout V1.0.0
:: #�ύ���д���
git pull 
git commit -am "JSDK-1 �Զ��ű��ύ(�汾������bug�Ż�)"  &&  git  push
:: # �л���master��֧
git checkout master

git pull origin master

:: # �ϲ�V1.0.0 ��֧����
git merge V1.0.0
git status
:: # �ύmaster��֧��originԶ�ֿ̲�
git push origin master
:: # ��������汾��
:: mvn clean install deploy -Dmaven.test.skip=true -P release
PAUSE
:: # �ύmaster��֧��githubԶ�ֿ̲�
git push github master
:: # �л���V1.0.0��֧
git checkout V1.0.0

PAUSE
