import { useState } from 'react'
import './App.css'
import { fetchChatResponse } from './services/api';
import ChatResponse from './components/ChatResponse';
import ChatInput from './components/ChatInput';

function App() {
  const [response, setResponse] = useState(null);
  const [loading, setLoading] = useState(false);

  const handleQuestionSubmit = async (question) => {
    setLoading(true);
    setResponse(null);
    try {
      const apiResponse = await fetchChatResponse(question);
      setResponse(apiResponse);
    } catch (error) {
      alert("Failed to get response")
    } finally {
      setLoading(false);
    }
  }

  return (
    <div className='App'>
      <header>
        <div className="header-content">
          <h1>Gemini ChatBot</h1>
          <div className="header-line"></div>
          <p className="header-subtitle">Your AI Assistant</p>
        </div>
      </header>

      <ChatInput onSubmit={handleQuestionSubmit}/>
      
      {loading && (
        <div className="loading-container">
          <div className="loader">
            <div className="loader-ring"></div>
            <div className="loader-ring"></div>
            <div className="loader-ring"></div>
          </div>
          <div className="loading-text">Processing your request...</div>
        </div>
      )}
      
      <ChatResponse response={response} />
    </div>
  )
}

export default App