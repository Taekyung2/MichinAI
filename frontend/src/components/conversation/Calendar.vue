<template>
  <v-row>
    <v-col cols="12" sm="6" md="4">
      <v-menu
        ref="menu"
        v-model="menu"
        :close-on-content-click="false"
        :return-value.sync="date"
        transition="scale-transition"
        offset-y
        min-width="290px"
      >
        <template v-slot:activator="{ on, attrs }">
          <v-text-field
            v-model="date"
            prepend-icon="mdi-calendar"
            readonly
            v-bind="attrs"
            v-on="on"
          ></v-text-field>
        </template>
        <v-date-picker 
          v-model="date"
          no-title
          scrollable
          :min="minDate"
          :max="maxDate"
          @change="getConversation">
          <v-spacer></v-spacer>
          <v-btn text color="primary" @click="menu = false">Cancel</v-btn>
          <v-btn text color="primary" @click="$refs.menu.save(date)">OK</v-btn>
        </v-date-picker>
      </v-menu>
    </v-col>
    <v-spacer></v-spacer>
  </v-row>
</template>

<script>
import axios from 'axios'
  export default {
    data: () => ({
      date: new Date().toISOString().substr(0, 10),
      maxDate: new Date().toISOString().substr(0, 10),
      // minDate: new Date().toISOString().substr(0, 10),
      menu: false,
      modal: false,
      menu2: false,
    }),
    computed: {
     minDate() {
       var minDate = new Date();
       minDate.setFullYear(minDate.getFullYear()-1);
       return minDate.toISOString().slice(0,10)
      }
    },
    methods: {
      getConversation() {
        let tmp = this.$store;
//      return axios.get('/api/conv/' + Calendar.date)
        axios.get('/api/conv/' + this.date)
          .then((response) => {
            console.log(response.data.convList);
            console.log(tmp);
            this.$store.commit('SET_CONVERSATIONLIST', response.data.convList);
            console.log(tmp.getters.getConversationList);
          });
      }
    },
    mounted () {
      
        let tmp = this.$store;
//      return axios.get('/api/conv/' + Calendar.date)
        axios.get('/api/conv/' + this.date)
          .then((response) => {
            console.log(response.data.convList);
            console.log(tmp);
            this.$store.commit('SET_CONVERSATIONLIST', response.data.convList);
            console.log(tmp.getters.getConversationList);
          });
      
    }
  }
</script>