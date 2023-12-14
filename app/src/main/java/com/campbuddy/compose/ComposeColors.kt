package com.campbuddy.compose

import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun editTextColors() = TextFieldDefaults.outlinedTextFieldColors(
    //textColor = colors.b,
    //cursorColor = colors.b,
    //focusedBorderColor = colors.a,
    //focusedLabelColor = colors.a,
    //unfocusedBorderColor = colors.b,
    //unfocusedLabelColor = colors.b,
    //disabledTextColor = colors.b,
    //disabledBorderColor = colors.b,
    //disabledLabelColor = colors.b
)

@Composable
fun switchColors() = SwitchDefaults.colors(
    //checkedThumbColor = colors.a,
    //checkedTrackColor = colors.b,
    //uncheckedThumbColor = colors.b,
    //uncheckedTrackColor = colors.c,
)

@Composable
fun radioButtonColors() = RadioButtonDefaults.colors(
    //selectedColor = colors.a,
    //unselectedColor = colors.c,
)

@Composable
fun checkBoxColors() = CheckboxDefaults.colors(
    //checkedColor = colors.b,
    //uncheckedColor = colors.a,
    //checkmarkColor = colors.background
)