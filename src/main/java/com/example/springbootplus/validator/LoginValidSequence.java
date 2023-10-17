package com.example.springbootplus.validator;

import javax.validation.GroupSequence;

@GroupSequence({UserNameValid.class,PasswordValid.class,CpachaValid.class})
public interface LoginValidSequence {



}
