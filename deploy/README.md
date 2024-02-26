# DEVoca Deploy Guides

DEVoca 배포 방법 설명입니다.

## 환경 변수 설정

```bash
cp .env.template .env

vi .env
# 환경 변수 값 수정
# MYSQL_EXTERNAL_PORT=3306
# MYSQL_PORT=3306
# MYSQL_ROOT_PASSWORD=your_root_password
# ...
```

### Run

```bash
docker network create devoca_network
docker compose up -d redis
docker compose up -d --build api
docker compose up -d --build frontend && docker compose stop frontend && docker compose rm -f frontend
docker compose up -d nginx
```

## MySQL DB

- Version: 8.0.35
- Docker container

## Jenkins setup

- Version: 2.442
- Docker container

- Go to <http://${HOST}:${JENKINS_PORT}>

### Plugin 설치

- Docker Pipeline
- docker-build-step
- Docker Compose Build Step
- GitLab

## Author

- [@cheesecat47](https://github.com/cheesecat47)
