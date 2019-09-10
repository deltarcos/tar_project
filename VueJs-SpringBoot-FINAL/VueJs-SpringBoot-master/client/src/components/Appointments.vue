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

              <!-- Select Doctor Combobox -->
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
          <!-- ///////////End Select Doctor Combobox -->

          <!-- Select Duration Combobox -->
                <v-row>
              <v-col>
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

              <v-col md="6">
                        <v-menu ref="menu" v-model="menu"
                            :close-on-content-click="false"
                            :return-value.sync="date"
                            transition="scale-transition"
                            offset-y
                            full-width
                            min-width="290px"
                        ><template v-slot:activator="{ on }">
                            <v-text-field v-model="date" label="Select Date" prepend-icon="event" readonly v-on="on"
                            ></v-text-field>
                        </template>
                        
                        <v-date-picker v-model="date" no-title scrollable>
                            <div class="flex-grow-1"></div>
                                <v-btn text color="primary" @click="menu = false">Cancel</v-btn>
                            <v-btn text color="primary" @click="$refs.menu.save(date)">OK</v-btn>
                        </v-date-picker>
                        </v-menu>
                        </v-col>

            </v-row>
            <!-- ///////////End Select Time Combobox -->

            <!-- Select Room Combobox -->
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
            <!-- ///////////End Select Room Combobox -->

            <!-- Detail Text area -->
            <v-row>
              <v-col>
                  <v-textarea block prepend-icon="comment" label="รายละเอียด" v-model="appointments.detail"></v-textarea>
              </v-col>
            </v-row>
            <!-- ///////////End Detail Text area -->

            <v-row justify="center">
              <v-col cols="12">
                <v-btn @click="saveAppointments" :class="{ red: !valid, green: valid }">submit</v-btn>
                <v-btn style="margin-left: 15px;" @click="clear">clear</v-btn>
              </v-col>
            </v-row>
            <br />
          </div>
        </v-form>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import http from "../http-common";

export default {
  name: "appointments",
  data() {
    return {
      appointments: {
        patientId: "",
        employeeId: "",
        roomId: "",
        durationId: "",
        detail: "",

      date: new Date().toISOString().substr(0, 10),


      },
      valid: false,
      patientCheck: false,
      patientName: ""
    };
  },
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
      http
        .post(
          "/appointments/" + this.appointments.patientId + "/" + this.appointments.employeeId + "/" +this.appointments.roomId +
            "/" + this.appointments.durationId + "/" + this.appointments.date + "/" + this.appointments.detail,this.appointments 
            //+ "/" + this.appointments.date,this.appointments
        )
       //.then(response => {
       //   console.log(response);
      //    this.$router.push("/view");
       // })
        .catch(e => {
          console.log(e);
        });
      this.submitted = true;
    },
    clear() {
      this.$refs.form.reset();
      this.patientCheck = false;
    },
    refreshList() {
     this.getEmployees();
     this.Rooms();
     this.getDurations();
    }

    
    /* eslint-enable no-console */
  },
  mounted() {
    this.getEmployees();
    this.getRooms();
    this.getDurations();
  }
};


</script>
