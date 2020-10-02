<template>
  <div class="container">
    <div class="wordbook-list-container" align="center">
      <v-list>
        <v-list-item-group
          v-model="selectedMenuItem"
          mandatory
          color="var(--main-point-color)"
          class="wordbook-menu-group"
        >
          <v-list-item class="menu-l wordbook-menu-item rounded-l-xl">
            <span>내 단어장</span>
          </v-list-item>
          <v-list-item class="menu-r wordbook-menu-item rounded-r-xl"> 
            <span>기본 단어장</span>
          </v-list-item>
        </v-list-item-group>
      </v-list>
      <div class="selected_menu-container">
        <!-- 내 단어장 -->
        <v-row v-if="selectedMenuItem==0">
          <UserWordbookListItem 
            :wordbook="wordbook"
            v-for="wordbook in wordbookList" :key="wordbook.id"/>
        </v-row>
        <!-- 기본 단어장 -->
        <div v-if="selectedMenuItem==1">
          <BaseWordbookListItem/>
        </div>
      </div>
      
    </div>
    <div>
      <!-- <WordbookFAB/> -->
      <!-- <WordbookCreate /> -->
    </div>

  </div>
</template>

<script>
import UserWordbookListItem from '@/components/wordbook/UserWordbookListItem.vue'
import BaseWordbookListItem from '@/components/wordbook/BaseWordbookListItem.vue'

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
        selectedMenuItem : 0,
        
      }
    },
    components: {
      UserWordbookListItem,
      BaseWordbookListItem,
      // WordbookCreate,
      // WordbookFAB
    }

}
</script>

<style>

.wordbook-menu-group{
  display: flex;
  border: 1px solid var(--border-base-color);
  border-radius: 24px !important;
  width: 230px;
  height: 48px;
}
.wordbook-menu-item{
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 0 0;
}

.menu-l.v-list-item--link:before{
  border-top-left-radius: 24px !important;
  border-bottom-left-radius: 24px !important;
}

.menu-r.v-list-item--link:before{
  border-top-right-radius: 24px !important;
  border-bottom-right-radius: 24px !important;
}

.selected_menu-container{
  display: flex;
}

</style>