<template>
    <div class="ox-container">
    <div class="ox-btn"> 
        <v-btn
            class="ox-btn"
            color="success"
            dark
        >
            <v-icon>mdi-circle-outline</v-icon>
        </v-btn>
    </div>
    <div class="ox-btn">
        <v-btn
            class="ox-btn"
            color="error"
            dark
        >
            <v-icon>mdi-close</v-icon>
        </v-btn>
    </div>
    </div>  
</template>

<script>
export default {
    name: 'WordQuizTypeOX'
}
</script>

<style scoped>
.ox-container{
    display: flex;
}
.ox-btn{
    flex-basis: 50%
}
.v-btn{
    width: 100%
}
</style>