# 환율계산 프로젝트

## What you’ll need
- About 20 minutes
- A favorite text editor or IDE
- JDK 1.8 or later
- Gradle 4+
- SpringBoot 2.1.4.RELEASE
- Lombok 1.18.6
- Bootstrap 4.1.3
- Thymeleaf 
- Docker

## Module Guides
- batch : 환율정보 저장용 Redis Scheduling
- common : 통화계산 및 송금, 수취 국가 정보, 공통 Dependencies 주입용
- money : Web Application, Web-API
- thirdparty.external-api : 외부API(실시간환율정보API)
- thirdparty.persistence : 환율정보저장DB

## Getting Started Guides
 - Docker
 	- ```docker run --name redis-local -d -p 6379:6379 redis```
 - Module Run
 	-  batch  And money module run
 - Test URL
 	- loclahost:8080
- Test case
	- expected..

© 2019 Jaehyun Shim
