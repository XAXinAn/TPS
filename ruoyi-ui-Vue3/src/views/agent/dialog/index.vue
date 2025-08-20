<template>
  <div class="chat-container">
    <div class="chat-messages" ref="messagesContainer">
      <div v-for="(message, index) in messages" :key="index" :class="['message-row', `message-${message.role}`]">
        <div class="message-bubble">
          <div class="message-content">{{ message.content }}</div>
        </div>
      </div>
      <div v-if="isLoading" class="message-row message-assistant">
        <div class="message-bubble">
          <div class="message-content">正在思考中...</div>
        </div>
      </div>
    </div>
    <div class="chat-input-area">
      <el-input
        v-model="userInput"
        placeholder="请输入您的问题..."
        @keyup.enter="sendMessage"
        :disabled="isLoading"
        size="large"
      >
        <template #append>
          <el-button type="primary" @click="sendMessage" :disabled="isLoading || !userInput.trim()">发送</el-button>
        </template>
      </el-input>
    </div>
  </div>
</template>

<script setup>
import { ref, nextTick } from 'vue';

// --- 响应式状态定义 ---
const messagesContainer = ref(null); // 对话容器的引用，用于滚动
const userInput = ref(''); // 用户输入
const isLoading = ref(false); // 是否正在等待AI响应
const messages = ref([
  { role: 'assistant', content: '您好！我是您的智能助理，有什么可以帮助您的吗？' }
]); // 对话历史

/**
 * 滚动到最新的消息
 */
async function scrollToBottom() {
  await nextTick();
  if (messagesContainer.value) {
    messagesContainer.value.scrollTop = messagesContainer.value.scrollHeight;
  }
}

/**
 * 发送消息
 */
async function sendMessage() {
  const text = userInput.value.trim();
  if (!text || isLoading.value) return;

  // 1. 将用户消息添加到对话历史
  messages.value.push({ role: 'user', content: text });
  userInput.value = '';
  isLoading.value = true;
  await scrollToBottom();

  // 2. TODO: 在这里调用您后端的智能体API
  // 以下是模拟API调用的示例代码
  simulateAgentApiCall(text);
}

/**
 * 模拟后端API调用
 * @param {string} userText 用户发送的文本
 */
function simulateAgentApiCall(userText) {
  setTimeout(() => {
    // 3. 接收到AI的响应后，将其添加到对话历史
    const aiResponse = `关于“${userText}”，我的回复是：这是一个模拟的响应。请在这里替换成您从后端获取的真实数据。`;
    messages.value.push({ role: 'assistant', content: aiResponse });
    isLoading.value = false;
    scrollToBottom();
  }, 1500); // 模拟1.5秒的网络延迟
}

</script>

<style scoped>
.chat-container {
  display: flex;
  flex-direction: column;
  height: calc(100vh - 150px); /* 根据您的布局调整高度 */
  width: 100%;
  max-width: 900px;
  margin: 0 auto;
  border: 1px solid #dcdfe6;
  border-radius: 8px;
  overflow: hidden;
}

.chat-messages {
  flex-grow: 1;
  padding: 20px;
  overflow-y: auto;
  background-color: #f5f7fa;
}

.message-row {
  display: flex;
  margin-bottom: 15px;
}

.message-assistant {
  justify-content: flex-start;
}

.message-user {
  justify-content: flex-end;
}

.message-bubble {
  max-width: 70%;
  padding: 10px 15px;
  border-radius: 18px;
  color: #fff;
}

.message-assistant .message-bubble {
  background-color: #409EFF;
}

.message-user .message-bubble {
  background-color: #67c23a;
}

.message-content {
  white-space: pre-wrap; /* 保持文本换行 */
  word-break: break-word;
}

.chat-input-area {
  padding: 15px;
  border-top: 1px solid #dcdfe6;
  background-color: #fff;
}
</style>
