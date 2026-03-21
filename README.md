# AI Financial News Analyzer

AI 기반 금융 뉴스 수집 및 감성 분석 백엔드 시스템

Spring Boot와 FastAPI 기반 AI 서비스를 분리한 마이크로서비스 구조로  
외부 금융 뉴스를 자동 수집하고, AI 감성 분석 및 요약을 수행한 뒤  
데이터를 저장하고 통계를 제공하는 프로젝트입니다.

---

# System Architecture

![Architecture](./docs/financial-news-ai-architecture.png)

---

# Tech Stack

### Backend
- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- Maven

### AI Service
- Python 3.11
- FastAPI
- Uvicorn

### Database
- MySQL 8

### Infrastructure
- Docker
- Docker Compose
- GitHub

---

# ERD

![ERD](./docs/financial-news-ai-erd.png)

---

# Features

## 1. 뉴스 자동 수집 (Scheduler)

외부 뉴스 API를 통해 금융 뉴스를 주기적으로 자동 수집합니다.

- Spring Scheduler 기반 배치 처리
- 특정 키워드(NVIDIA 등) 기준 뉴스 수집
- 일정 주기마다 자동 실행

---

## 2. AI 감성 분석 및 요약

수집된 뉴스 데이터를 AI 서비스로 전달하여 다음을 수행합니다.

- 감성 분석 (positive / neutral / negative)
- 점수 기반 정량 평가
- 뉴스 요약 처리

---

## 3. 중복 데이터 방지

뉴스 URL을 기준으로 중복 데이터를 방지합니다.

- DB unique 제약 조건 적용
- 저장 전 URL 존재 여부 검증
- 중복 뉴스 필터링

---

## 4. 뉴스 저장 API

뉴스 데이터를 입력하면 AI 분석 후 저장합니다.

### POST /api/news

```json
{
  "symbol": "NVDA",
  "title": "Nvidia stock surges after record profit growth",
  "summary": "Strong growth and record profit improved market sentiment."
}