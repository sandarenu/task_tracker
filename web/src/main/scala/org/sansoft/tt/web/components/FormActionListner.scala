package org.sansoft.tt.web.components

trait FormActionListner {

  def formSubmitSuccess(savedObject : Any);

  def formCancled();
}