<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h2 class="font-weight-black font-italic text-uppercase display-4 text-center">Appointment</h2>
      </v-flex>
    </v-layout>

    <v-row justify="center">
      <v-col cols="4">
        <v-form v-model="valid" ref="form">
          <v-row justify="center">
            <!-- Patient ID text field -->
            <v-col cols="10">
              <v-text-field 
                block 
                prepend-icon="accessible" 
                label="รหัสผู้ป่วย"
                 v-model="appointments.patientId"     
                :rules="[(v) => !!v || 'กรุณากรอกข้อมูล']"
                required
                >
                </v-text-field>
             

              <p v-if="patientCheck != ''">ชื่อผู้ป่วย : {{patientName}}</p>
              

            </v-col>
            <!-- ///////////Patient ID text field -->

            <!-- Search BT -->
            <v-col cols="2">
              <div class="my-2">
                <v-btn color="blue-grey darken-1" dark @click="findPatients" >Serach </v-btn>
              </div>
            </v-col>
            <!-- ///////////End Search BT -->

          </v-row>

          <div v-if="patientCheck">

              <!-- เลือกแพทย์ Combobox -->
          <v-row justify="center">
            <v-col>
                <v-select
                  label="แพทย์ผู้ออกใบนัด"
                  v-model="appointments.employeeId"
                  
                  :items="employees"
                  item-text="name"
                  item-value="id"
                  prepend-icon="supervised_user_circle"
                  :rules="[(v) => !!v || 'กรุณากรอกข้อมูล']"
                  required
                ></v-select>
              </v-col>
               </v-row>
          <!-- ///////////จบส่วน เลือกแพทย์ Combobox -->

          <!-- เลือกช่วงเวลา Combobox -->
                <v-row>
              <v-col cols>
                <v-select
                  label="เวลา"
                  v-model="appointments.durationId"
                  :items="durations"
                  item-text="name"
                  item-value="id"
                  prepend-icon="access_time"
                  :rules="[(v) => !!v || 'กรุณากรอกข้อมูล']"
                  required
                ></v-select>
              </v-col>
              </v-row>
              <!-- ///////////จบส่วน เลือกช่วงเวลา Combobox -->

        <!-- เลือกวันนัด -->
        <v-row>
        <v-col >
        <v-menu
          v-model="menu1"
          :close-on-content-click="false"
          full-width
          max-width="290"
        >
          <template v-slot:activator="{ on }">
            <v-text-field
              :value="computedDateFormattedMomentjs"
              clearable
              label="เลือกวันนัด"
              readonly
              prepend-icon="event"
              v-on="on"
              locale="th"
            ></v-text-field>
          </template>
          <v-date-picker
          locale="th"
            v-model="date"
            @change="menu1 = false"
          ></v-date-picker>
        </v-menu>
      </v-col>
      </v-row>
        <!-- ///////////////////////////////////////////// จบส่วนเลือกวันนัด -->

            <!-- เลือกห้องตรวจ Combobox -->
            <v-row>
              <v-col>
                <v-select
                  label="ห้องตรวจ"
                  v-model="appointments.roomId"
                  :items="rooms"
                  item-text="title"
                  item-value="id"
                  prepend-icon="room"
                  :rules="[(v) => !!v || 'กรุณากรอกข้อมูล']"
                  required
                ></v-select>
              </v-col>
            </v-row>
            <!-- ///////////จบส่วนเลือกห้องตรวจ Combobox -->

            <!-- รายละเอียด Text area -->
            <v-row>
              <v-col>
                  <v-textarea block prepend-icon="comment" label="รายละเอียด" v-model="appointments.detail"></v-textarea>
              </v-col>
            </v-row>
            <!-- ///////////จบส่วน รายละเอียด Text area -->

           <!--ปุ่ม Clear -->
            <v-row justify="center">
                
                <!-- <v-btn class="mt-6" style="margin-left: 15px;" @click="clear">ล้างข้อมูล</v-btn> -->
                <div class="text-center">
                  <!--สรุปรายละเอียดการนัด Button Sheet -->
    <v-bottom-sheet v-model="sheet">
      <template v-slot:activator="{ on }">
        <v-btn prepend-icon="check_box" :class="{ red: !valid, green: valid } " dark v-on="on">สรุปรายละเอียด</v-btn>
      </template>
  
      <v-sheet class="text-center" height="500px">
        

        <div id="printMe">
          <v-container>
            <v-row justify="center">
             <v-col justify="center" cols="6">
            <v-form v-model="valid" ref="form" >
            <!-- SOURCE -->
          <h1>รายละเอียดการออกใบนัด</h1> <br> 
           <!--patientName: "",
      EmployeeName: "",
      RoomTitle:"",
      
      DutationTitle:"",
      detailPrint:"",-->
       <v-row>
          <v-col cols="5">
              <p class="text-left">แพทย์ผู้ออกใบนัด : {{this.EmployeeName}}} </p>
            </v-col>
          </v-row>  
       <v-row>
            <v-col cols="5">
              <p class="text-left">รหัสผู้ป่วย : {{this.appointments.patientId}}</p>
            </v-col>
            <v-col>
              <p class="text-left" >ชื่อผู้ป่วย : คุณ{{patientName}}</p>
            </v-col>
        </v-row> 
        <v-row>
            <v-col cols="5">
              <p class="text-left">วันที่นัด : {{this.appmDate}}</p>
            </v-col>
            <v-col cols="5">
              <p class="text-left">เวลานัด : {{this.appmDate}}</p>
            </v-col>
        </v-row>
        <v-row>
          <v-col cols="5">
              <p class="text-left">ห้อง : {{this.appointments.roomId}}</p>
            </v-col>
          </v-row> 
       <v-row>
          <v-col cols="5">
              <p class="text-left">รายละเอียด : {{this.appointments.detail}}</p>
            </v-col>
          </v-row> 

    </v-form>
     </v-col>
    </v-row>
     </v-container>
    </div>
  <!-- OUTPUT -->
         <v-row justify="center">
          <v-col cols="2">
          <v-btn @click="sheet = !sheet" class="mt-2" tile outlined color="amber">
            <v-icon left>mdi-pencil</v-icon> แก้ไขข้อมูล</v-btn>
            </v-col> <v-col cols="2">
          <v-btn @click="saveAppointments" class="mt-2" tile outlined color="deep-purple darken-1">
            <v-icon left>save</v-icon> บันทึกข้อมูลและพิมพ์</v-btn> </v-col> 
         </v-row>
          
      </v-sheet>
    </v-bottom-sheet>
  </div>
            </v-row>
            <br />
          </div>
        </v-form>
      </v-col>
    </v-row>
  </v-container>
  
</template>

<script>
import http from "../http-common"
import moment from 'moment'
import Vue from 'vue';
import VueHtmlToPaper from 'vue-html-to-paper';

const options = {
  name: '_blank',
  specs: [
    'fullscreen=yes',
    'titlebar=yes',
    'scrollbars=yes'
  ],
  styles: [
    'https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css',
    'https://unpkg.com/kidlat-css/css/kidlat.css'
  ]
}
 
Vue.use(VueHtmlToPaper, options);
 
// or, using the defaults with no stylesheet
 
Vue.use(VueHtmlToPaper);

export default {

  computed: {
      computedDateFormattedMomentjs () {
        return this.date ? moment(this.date).format('dddd Do, MMMM YYYY') : ''
      },
    },
    
  name: "appointments",
  data() {
    return {
      appointments: {
        patientId: "",
        employeeId: "",
        roomId: "",
        durationId: "",
        detail: "",
      },
      sheet: false,
      valid: false,
      patientCheck: false,
      patientName: "",
      EmployeeName: "",
      RoomTitle:"",
      DutationTitle:"",
      detailPrint:"",
      output: null,
      // ส่วนของ DatePicker
      appmDate : new Date().toISOString().substr(0, 10),
      menu: false,
      modal: false,
      menu2: false,
      date : new Date().toISOString().substr(0, 10),
    };
  },
//-----------------------------------------------------------------------METTODS----------------------------
  methods: {
    
    /* eslint-disable no-console */

    // ดึงข้อมูล Employee ใส่ combobox
    getEmployees() {
      http
        .get("/employee")
        .then(response => {
          this.employees = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // ดึงข้อมูล Room ใส่ combobox
    getRooms() {
      http
        .get("/room")
        .then(response => {
          this.rooms = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // ดึงข้อมูล Duration ใส่ combobox
    getDurations() {
      http
        .get("/duration")
        .then(response => {
          this.durations = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // function ค้นหา Patient ด้วย ID
    findPatients() {
      http
        .get("/patient/" + this.appointments.patientId)
        .then(response => {
          console.log(response);
          if (response.data != null) {
            this.patientName = response.data.name;
            this.patientCheck = response.status;
          } else {
            this.clear()
          }          
        })
        .catch(e => {
          console.log(e);
        });
      this.submitted = true;
    },
    // function เมื่อกดปุ่ม submit
    saveAppointments() {
      console.log(this.appointments);
      http
        .post(
          "/appointments/" + this.appointments.patientId + "/" + this.appointments.employeeId + "/" +this.appointments.roomId +
            "/" + this.appointments.durationId + "/" + this.date + "/" + this.appointments.detail,this.appointments 
        )
       .then(response => {
          console.log(response);
          this.$router.push("/viewAppm");
           //ปริ้น
          this.$htmlToPaper('printMe');
        })
        .catch(e => {
          console.log(e);
        });
      this.submitted = true;
    },
    //ปริ้นใบนัด
    clear() {
      this.$refs.form.reset();
      this.patientCheck = false;
    },
    refreshList() {
     this.getEmployees();
     this.Rooms();
     this.getDurations();
     this.menu1();
    },

    
    /* eslint-enable no-console */
    },
  mounted() {
    this.getEmployees();
    this.getRooms();
    this.getDurations();
  }
};


 

</script>
