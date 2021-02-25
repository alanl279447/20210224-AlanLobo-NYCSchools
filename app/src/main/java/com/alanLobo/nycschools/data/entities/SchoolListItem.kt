package com.example.nycschools.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "schoolListItem")
data class SchoolListItem (
    val academicopportunities1: String?,
    val academicopportunities2: String?,
    val academicopportunities3: String?,
    val admissionspriority11: String?,
    val admissionspriority21: String?,
    val admissionspriority31: String?,
    val admissionspriority41: String?,
    val advancedplacement_courses: String?,
    val attendance_rate: String?,
    val bbl: String?,
    val bin: String?,
    val boro: String?,
    val borough: String?,
    val building_code: String?,
    val bus: String?,
    val census_tract: String?,
    val city: String?,
    val code1: String?,
    val college_career_rate: String?,
    val community_board: String?,
    val council_district: String?,
    @PrimaryKey
    val dbn: String,
    val ell_programs: String?,
    val end_time: String?,
    val extracurricular_activities: String?,
    val fax_number: String?,
    val finalgrades: String?,
    val grade9geapplicants1: String?,
    val grade9geapplicantsperseat1: String?,
    val grade9gefilledflag1: String?,
    val grade9swdapplicants1: String?,
    val grade9swdapplicantsperseat1: String?,
    val grade9swdfilledflag1: String?,
    val grades2018: String?,
    val graduation_rate: String?,
    val interest1: String?,
    val language_classes: String?,
    val latitude: String?,
    val location: String?,
    val longitude: String?,
    val method1: String?,
    val neighborhood: String?,
    val nta: String?,
    val offer_rate1: String?,
    @SerializedName("overview_paragraph")
    val overview_paragraph: String?,
    val pct_stu_enough_variety: String?,
    val pct_stu_safe: String?,
    val phone_number: String?,
    val prgdesc1: String?,
    val primary_address_line_1: String?,
    val program1: String?,
    val psal_sports_boys: String?,
    val psal_sports_coed: String?,
    val psal_sports_girls: String?,
    val school_10th_seats: String?,
    val school_accessibility_description: String?,
    val school_email: String?,
    @SerializedName("school_name")
    val school_name: String?,
    val school_sports: String?,
    val seats101: String?,
    val seats9ge1: String?,
    val seats9swd1: String?,
    val shared_space: String?,
    val start_time: String?,
    val state_code: String?,
    val subway: String?,
    val total_students: String?,
    val website: String?,
    val zip: String?
) : Parcelable