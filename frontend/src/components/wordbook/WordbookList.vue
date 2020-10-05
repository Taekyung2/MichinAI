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

      <!-- 내 단어장 -->
      <v-row v-if="selectedMenuItem==0" key="userWordbook">
        <UserWordbookListItem 
          :wordbook="wordbook"
          v-for="wordbook in wordbooklist" :key="wordbook.id"/>
      </v-row>
      <!-- 기본 단어장 -->
      <div v-if="selectedMenuItem==1" key="baseWordbook">
        <BaseWordbookListItem/>
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
import { mapGetters } from 'vuex'

// import WordbookCreate from '@/components/wordbook/WordbookCreate.vue'
// import WordbookFAB from '@/components/wordbook/WordbookFAB.vue'

export default {
    name: 'WordbookList',
    data() {
      return {
        selectedMenuItem : 0,
        wordbooklist : []
      }
    },
    components: {
      UserWordbookListItem,
      BaseWordbookListItem,
      // WordbookCreate,
      // WordbookFAB
    },
    mounted() {
      console.log(this.wordbooklist)
      this.wordbooklist = this.getmyWordbook
    },
    computed : {
      ...mapGetters([ 'getmyWordbook'])
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
  margin-bottom: 10px;
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