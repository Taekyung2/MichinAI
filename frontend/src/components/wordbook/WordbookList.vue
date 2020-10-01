<template>
  <div class="container">
    <div class='wordbook-list-container'>
      <v-row>
      <WordbookListItem 
        :wordbook="wordbook"
        v-for="wordbook in wordbookList" :key="wordbook.id"/>
      </v-row>
    </div>
    <div>
      <!-- <WordbookFAB/> -->
      <!-- <WordbookCreate /> -->
    </div>

  </div>
</template>

<script>
import WordbookListItem from '@/components/wordbook/WordbookListItem.vue'
// import WordbookCreate from '@/components/wordbook/WordbookCreate.vue'
// import WordbookFAB from '@/components/wordbook/WordbookFAB.vue'

export default {
    name: 'WordbookList',
    data() {
      return {
        wordbookList : [
            {id:'1',name:'workshop -1', total:8},
            {id:'2',name:'workshop -2', total:12},
            {id:'3',name:'workshop -3', total:100},
            {id:'4',name:'workshop -4', total:3},
            {id:'5',name:'workshop -5', total:10},
            {id:'6',name:'workshop -6', total:20},
            {id:'7',name:'workshop -7', total:33},
            {id:'8',name:'workshop -8', total:50},
        ],
        
      }
    },
    components: {
      WordbookListItem,
      // WordbookCreate,
      // WordbookFAB
    }

}
</script>

<style>

</style>