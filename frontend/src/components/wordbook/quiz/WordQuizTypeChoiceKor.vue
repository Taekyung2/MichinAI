<template>
    <div class="choice-container">
        <v-btn 
            v-for="(choiceKor, index) in choiceKorList" 
            :key="index"
            class="choice-btn"
        >
            {{choiceKor}}
        </v-btn>
    </div>
</template>

<script>
export default {
    name: 'WordQuizTypeChoiceKor',
    props:{
        choiceKorList: Array,
    }
}
</script>

<style>
.choice-container{
    display: flex;
    flex-direction: column;
}
.choice-btn{
    margin: 5px 0;
}

</style>