<template>
    <div class='container'>
        <h2>단어장 화면</h2>   
        <WordbookList/> 
    </div>
</template>

<script>
import WordbookList from '@/components/wordbook/WordbookList.vue'
export default {
    name: 'Wordbook',
    components: {
        WordbookList,
    },
}
</script>

<style>

</style>