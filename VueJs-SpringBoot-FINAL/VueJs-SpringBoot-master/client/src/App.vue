<template>
  <v-app>
    <v-app-bar app class="cyan darken-1">
       <v-btn @click="drawer=!drawer" class="ma-2" tile large color="white" icon>
      <v-icon>menu</v-icon>
       </v-btn>
      <v-toolbar-title class="headline text-uppercase">
        <span>DOCTOR</span>
        <span class="font-weight-light"> information system</span>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn text href="#" target="_blank">
        <v-icon left>home</v-icon> 
        <span>หน้าแรก</span>
      </v-btn>
      <v-btn text href="#" target="_blank">
        <v-icon left>input</v-icon> 
        <span>ออกจากระบบ</span>
      </v-btn>
    </v-app-bar>
 

    <v-content>
      <!-- <Appointments/> -->
      <router-view />
    </v-content>
  </v-app>
</template>

<script>


export default {
  name: 'App',
  data() {
    return {
    username: 'Dr.Too Poptorn',
    drawer:true
    }
  },
}
</script>
