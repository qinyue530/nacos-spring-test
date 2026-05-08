# Nacos Spring Test

基于 Nacos 的微服务测试项目，包含服务注册发现、配置中心、网关、认证等多个模块。

## 项目简介

本项目是一个微服务架构的学习和实践项目，使用 Spring Cloud Alibaba 技术栈，集成 Nacos 作为服务注册中心和配置中心。

## 技术栈

- **框架**: Spring Boot / Spring Cloud Alibaba
- **服务注册/配置**: Nacos
- **服务网关**: Spring Cloud Gateway
- **认证授权**: Spring Security OAuth2
- **分布式事务**: Seata
- **链路追踪**: SkyWalking
- **任务调度**: XXL-Job
- **反向代理**: Nginx

## 项目模块

| 模块 | 说明 |
|------|------|
| `ServiceConfig` | 配置中心服务 |
| `ServiceConsumer` | 服务消费者 |
| `ServiceGateway` | API 网关服务 |
| `ServiceOauth` | 认证授权服务 |
| `ServiceProducer` | 服务提供者 |
| `ServiceSkywalking` | 链路追踪集成 |
| `core` | 公共核心模块 |

## 基础设施

- `nacos/` - Nacos 服务注册与配置中心
- `seata/` - 分布式事务协调器
- `nginx/` - 反向代理配置
- `xxl-job/` - 分布式任务调度

## 快速开始

### 环境要求

- JDK 1.8+
- Maven 3.6+
- Docker (可选，用于运行中间件)

### 启动步骤

1. 启动 Nacos 服务
   ```bash
   cd nacos
   # 根据 startVM.txt 中的说明启动
   ```

2. 编译项目
   ```bash
   mvn clean install
   ```

3. 依次启动各服务模块

## 项目特点

- ✅ 服务注册与发现
- ✅ 统一配置管理
- ✅ API 网关路由
- ✅ 统一认证授权
- ✅ 分布式事务
- ✅ 链路追踪
- ✅ 任务调度

---

*创建于 2023-12-05*
