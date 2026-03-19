from fastapi import FastAPI
from pydantic import BaseModel

app = FastAPI()

class AnalyzeRequest(BaseModel):
    text: str

positive_words = ["surge", "growth", "profit", "gain", "record", "strong", "improved", "rise"]
negative_words = ["decline", "loss", "drop", "crash", "risk", "weak", "fall"]

@app.get("/health")
def health():
    return {"status": "ok"}

@app.post("/analyze")
def analyze(request: AnalyzeRequest):
    text = request.text.lower()
    score = 0

    for word in positive_words:
        if word in text:
            score += 1

    for word in negative_words:
        if word in text:
            score -= 1

    if score > 0:
        sentiment = "positive"
    elif score < 0:
        sentiment = "negative"
    else:
        sentiment = "neutral"

    return {
        "sentiment": sentiment,
        "score": score
    }

@app.post("/summarize")
def summarize(request: AnalyzeRequest):
    text = request.text.strip()

    if not text:
        return {"summary": ""}

    if len(text) <= 120:
        return {"summary": text}

    return {"summary": text[:120] + "..."}