# AI Financial News Analyzer

LLM 시장 뉴스 자동 수집 및 감성 분석 백엔드 시스템

Spring Boot와 FastAPI 기반 AI 서비스를 분리한 마이크로서비스 구조로  
OpenAI, Gemini, Claude 관련 뉴스를 자동 수집하고,  
감성 분석 및 요약을 수행한 뒤 데이터를 저장하고 통계를 제공하는 프로젝트입니다.

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

# Core Features

## 1. LLM 뉴스 자동 수집

OpenAI, Gemini, Claude 관련 뉴스를 외부 뉴스 API로부터 자동 수집합니다.

- Spring Scheduler 기반 주기적 수집
- LLM 키워드별 뉴스 데이터 적재
- 수동 수집 API와 자동 수집 스케줄러 동시 지원

---

## 2. 검색어와 저장용 식별자 분리

뉴스 검색 정확도와 데이터 일관성을 위해 query와 symbol을 분리했습니다.

- 저장용 symbol: `OPENAI`, `GEMINI`, `CLAUDE`
- 검색용 query: `OpenAI OR ChatGPT`, `Google Gemini`, `Anthropic Claude`

이를 통해 검색 품질을 높이면서도  
DB에서는 일관된 기준으로 데이터를 관리할 수 있습니다.

---

## 3. AI 감성 분석 및 요약

수집된 뉴스 제목과 본문 요약 데이터를 FastAPI 기반 AI 서비스로 전달하여 다음을 수행합니다.

- 감성 분석: `positive`, `neutral`, `negative`
- 점수 기반 감성 평가
- 뉴스 요약 생성

---

## 4. URL 기반 중복 제거

동일 기사가 반복 저장되지 않도록 URL 기준 중복 제거 로직을 구현했습니다.

- DB unique 제약 조건 적용
- 저장 전 `existsByUrl()` 검사
- 중복 기사 저장 방지

---

## 5. 뉴스 조회 API

저장된 뉴스 데이터를 symbol 기준으로 조회할 수 있습니다.

- 종목/키워드별 조회
- 감성 기준 필터 조회 지원
- 최신순 정렬

---

## 6. 감성 통계 API

모델별 뉴스 감성 통계를 제공합니다.

예시:
- 평균 감성 점수
- 긍정 / 부정 / 중립 기사 수
- 전체 기사 수

### Example
- `GET /api/news/stats/OPENAI`
- `GET /api/news/stats/GEMINI`
- `GET /api/news/stats/CLAUDE`

---

# API Examples

## 수동 뉴스 수집

### POST `/api/collector/news`

```bash
curl -X POST "http://localhost:8080/api/collector/news?symbol=OPENAI&query=OpenAI%20OR%20ChatGPT"