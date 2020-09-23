<template>
  <v-row justify="end">
      <v-btn class="mx-2" 
      fab dark color="indigo"
      @click.stop="dialog = true">
          <v-icon dark>mdi-plus</v-icon>
      </v-btn>

      <v-dialog
        v-model="dialog"
        max-width="290"
      >
        <v-card>
          <v-card-title class="headline">단어 추가</v-card-title>
          <v-card-text>
            <div>
                <v-text-field label="단어" :rules="rules" hide-details="auto"></v-text-field>
                <v-text-field label="뜻" :rules="rules" hide-details="auto"></v-text-field>
            </div>
          </v-card-text>

          <v-card-actions>
            <v-spacer></v-spacer>

            <v-btn
              color="green darken-1"
              text
              @click="dialog = false"
            >
              취소
            </v-btn>

            <v-btn
              color="green darken-1"
              text
              @click="dialog = false"
            >
              저장
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
</template>

<script>
  export default {
    name: 'WordCreate', 
    data () {
        return {
        dialog: false,
        rules: [
            value => !!value || 'Required.',
        ],
        }
    },
  }
</script>