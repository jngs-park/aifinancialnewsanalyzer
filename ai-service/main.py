from fastapi import FastAPI
from pydantic import BaseModel

app = FastAPI()

class NewsRequest(BaseModel):
    text: str

@app.get("/health")
def health():
    return {"status": "ok"}

@app.post("/analyze")
def analyze(req: NewsRequest):

    text = req.text.lower()

    positive_words = ["surge","rise","gain","record","profit","growth"]
    negative_words = ["drop","fall","loss","decline","crash","risk"]

    score = 0

    for w in positive_words:
        if w in text:
            score += 1

    for w in negative_words:
        if w in text:
            score -= 1

    sentiment = "neutral"

    if score > 0:
        sentiment = "positive"
    elif score < 0:
        sentiment = "negative"

    return {
        "sentiment": sentiment,
        "score": score
    }