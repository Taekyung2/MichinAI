<template>
<span>
    <v-btn class="mx-2" x-small fab dark color="var(--main-point-color)" @click.stop="dialog = true">
      <v-icon dark>mdi-plus</v-icon>
    </v-btn>

    <v-dialog v-model="dialog" max-width="290">
      <v-card>
        <v-card-title class="headline">단어 추가</v-card-title>
        <v-card-text>
          <div>
            <v-text-field
              label="단어"
              :rules="rules"
              hide-details="auto"
              v-model="addWordInfo.contents[0].eng"
            ></v-text-field>
            <v-text-field
              label="뜻"
              :rules="rules"
              hide-details="auto"
              v-model="addWordInfo.contents[0].kor"
            ></v-text-field>
          </div>
        </v-card-text>

        <v-card-actions>
          <v-spacer></v-spacer>

          <v-btn color="green darken-1" text @click="dialog = false">
            취소
          </v-btn>

          <v-btn color="green darken-1" text @click="addWord"> 저장 </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
</span>
</template>

<script>
import axios from "axios";
import SERVER from "@/api/spring.js";
import { mapActions } from "vuex";

export default {
  name: "WordCreate",
  data() {
    return {
      dialog: false,
      rules: [(value) => !!value || "Required."],
      payload: {},
      addWordInfo: {
        wordbook_id: "",
        contents: [
          {
            eng: "",
            kor: ""
          },
        ],
      },
    };
  },
  props: {
    wordbook_id: String,
    isBase: Boolean
  },
  methods: {
    ...mapActions(["selectedWordbook"]),

    addWord() {
      console.log(this.addWordInfo)
      axios
        .put(SERVER.URL + SERVER.ROUTES.addWord, this.addWordInfo)
        .then((res) => {
          this.payload = {wordbook: res.data, chk: this.isBase}
          this.selectedWordbook(this.payload);
          this.dialog = false;
        })
        .catch((err) => {
          console.log("error" + err.response);
        });
    },
  },
  created() {
    this.addWordInfo.wordbook_id = this.wordbook_id;
  },
};
</script>