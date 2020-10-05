<template>
<div class="wordbook-list">
    <v-card 
    outlined
    @click="selectedWordbook(wordbook)"
  >
    <v-list-item three-line>
      <v-list-item-content>
        <div class="wordbook-content">
            <span class="wordbook-name eng-text" style="margin: 0 auto">{{wordbook.name}}</span> 
        </div>
        
      </v-list-item-content>
    </v-list-item>
 <v-divider></v-divider>
  </v-card>

</div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
export default {
    name: 'WordbookListItem',
    props: {
      wordbook: Object,
    },
    methods: {
      ...mapActions(['selectedWordbook']),
    },
    computed : {
      ...mapGetters('Kakao', ['account'])
    },
}
</script>

<style>

.wordbook-list{
  flex: 1;
  background: white;
  margin : 10px 5px;
  flex-basis: 40%;

}

.wordbook-list :hover{
  background: var(--hover-background-color);
}

.wordbook-content{
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.wordbook-name{
  font-size: 20px;
  color: var(--font-base-color);
}
.wordbook-total{
  float: right;
  color: var(--font-soft-color);
}

</style>