<template>
    <div class='container'>
        <h2>점수 화면</h2>
        <div class='score-container'>
            <ChatScore/>
        </div>
        <div class='score-container'>
            <WordScore/>
        </div>
    </div>
</template>

<script>
import ChatScore from '@/components/score/ChatScore.vue'
import WordScore from '@/components/score/WordScore.vue'
export default {
    name: 'Score',
    components: {
        ChatScore,
        WordScore
    }

}
</script>

<style>
.score-container{
    margin: 50px 0;
}
</style>