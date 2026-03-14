from fastapi import FastAPI
from pydantic import BaseModel

app = FastAPI()


class SummarizeRequest(BaseModel):
    text: str


@app.get("/health")
def health():
    return {"status": "ai-service ok"}


@app.post("/ai/summarize")
def summarize(request: SummarizeRequest):
    text = request.text.strip()

    if not text:
        return {"summary": "요약할 텍스트가 없습니다."}

    summary = text[:100]
    return {"summary": f"요약 결과: {summary}"}