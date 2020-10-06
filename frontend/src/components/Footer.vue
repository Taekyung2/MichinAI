<template> 
 <v-footer
      padless
      fixed
      grow
    >
  
    <v-bottom-navigation
    v-model="value"
    shift
    color="var(--main-color)"
    class="footer-navigation"
  >
               
    <v-btn router-link to='/conversation'>
      <span class="footer-text">오늘의 회화</span>

      <v-icon>mdi-book-open-page-variant-outline</v-icon>
    </v-btn>

    <v-btn router-link to='/chatbot/list'>
      <span class="footer-text">챗봇</span>
      <v-icon>mdi-message-text</v-icon>
    </v-btn>

    <v-btn router-link to='/wordbook/list'>
      <span class="footer-text">단어장</span>

      <v-icon>mdi-bookshelf</v-icon>
    </v-btn>

    <v-btn router-link to='/score'> 
      <span class="footer-text">내점수</span>

      <v-icon>mdi-marker</v-icon>
    </v-btn>
  </v-bottom-navigation>
    </v-footer>
</template>

<script>
export default {
    name: 'Footer',
    data: () => ({ value: 0 }),
   
}
</script>

<style scoped>

.v-item-group.v-bottom-navigation{
  justify-content: space-around;
  padding: 0 10px;
}
.v-item-group.v-bottom-navigation .v-btn{
  min-width: 80px;
  width:100%
}
.v-btn:not(.v-btn--round).v-size--default{
  height: auto;
}

.v-item-group.v-bottom-navigation--shift .v-btn--active .v-btn__content > *:not(.v-icon){
  top: calc(100% - 5px)
}

.footer-text{
  font-family: 'BMJUA' !important;
  color: var(--main-color);
}

</style>