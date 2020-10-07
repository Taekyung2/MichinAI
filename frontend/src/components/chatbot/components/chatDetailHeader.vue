<template>
  <v-banner class="mb-2 pa-0">
    <!-- {{chat.date}} -->
    <template v-slot:actions class="pa-0">
      <h2>{{chat.date}}</h2>
      <v-spacer></v-spacer>
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

.padding {
  padding: 4px 4px 10px 4px !important;
  margin-bottom: 10px;
  vertical-align: center;
}

.v-banner__wrapper {
  padding: 5px 5px 15px 5px !important;
}

</style>