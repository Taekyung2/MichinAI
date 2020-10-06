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
          :allowed-dates="allowedDates"
          @change="getConversation"
          @update:picker-date="pickerUpdate($event)">
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
import moment from "moment"

import SERVER from '@/api/spring';
//import VueMomentJs from "vue-momentjs"
  export default {
    data: () => ({
      date: new Date().toISOString().substr(0, 10),
      maxDate: new Date().toISOString().substr(0, 10),
      // minDate: new Date().toISOString().substr(0, 10),
      menu: false,
      modal: false,
      menu2: false,
      loading : true,
      availableDates: [],
    }),
    computed: {
      // 날짜 지정 1년전까지로 제한해주는 함수
     minDate() {
       var minDate = new Date();
       minDate.setFullYear(minDate.getFullYear()-1);
       return minDate.toISOString().slice(0,10)
      }
    },
    methods: {
      // axios 통신해서 회화 가져옴
      getConversation() {
        axios.get(SERVER.URL + '/conv/' + this.date)
          .then((response) => {
            // 대화 인물 이름 설정 man1, woman1 둘이 대화함
            // response.data.convList.forEach(element => {
            //   if(element.speaker === 'man1') {
            //     element.speaker = 'man';
            //   } else {
            //     element.speaker = 'woman';
            //   }
            // });
            this.$store.commit('SET_CONVERSATIONLIST', response.data.convList);
          });
      },
      // 일요일 막기 위한 함수들
      allowedDates(a) {
        return this.availableDates.includes(a);
    },
    
    pickerUpdate: function(val) {
      let totalDay = moment(val, "YYYY-MM").daysInMonth()
      
      let availableDates = []        
      let day = 1

      for (let i = day; i <= totalDay ; i++) {
          let date = moment().month(val.split('-')[1]-1).date(i).format("YYYY-MM-DD")
          if (moment(date).day() !== 0)
            availableDates.push(date)
      }
      this.availableDates = availableDates;
      this.allowedDates();
    }
    
    },
    mounted () {
        // axios.get(SERVER.URL + '/conv/' + this.date)
        //   .then((response) => {
        //     // 대화하는 인물 이름 설정 man1, woman1 두개로 대화함
        //     response.data.convList.forEach(element => {
        //       if(element.speaker === 'man1') {
        //         element.speaker = 'man';
        //       } else {
        //         element.speaker = 'woman';
        //       }
        //     });
        //     this.$store.commit('SET_CONVERSATIONLIST', response.data.convList);
        //   });
        this.getConversation();
    }
  }
</script>