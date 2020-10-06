<template>
  <v-banner>
    {{chat.date}}
    <template v-slot:actions>
      <v-btn icon>
        <v-icon color="grey lighten-1" @click="back">mdi-arrow-left</v-icon>
      </v-btn>
      <v-btn icon>
        <v-icon color="grey lighten-1">mdi-delete-outline</v-icon>
      </v-btn>
    </template>
  </v-banner>
</template>



<script>
export default {
    name: 'chatDetailHeader',
   
   props: {
        chat: Object,
    },
   
   methods: {
    back(){
      this.$router.push({name:'ChatList'})
    },
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

</style>