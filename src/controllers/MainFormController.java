package controllers;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import hash.HashAlgorithms;
import hash.HashFile;

public class MainFormController implements Initializable {

	private File file;

	public File getFile() {
		return this.file;
	}

	@FXML
	private AnchorPane anchorPane;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Label LabelFile;

	@FXML
	private TextField TextFieldFile;

	@FXML
	private Button ButtonOpenFile;

	@FXML
	private TextField TextFieldMD5;

	@FXML
	private TextField TextFieldSHA1;

	@FXML
	private TextField TextFieldSHA2_224;

	@FXML
	private TextField TextFieldSHA2_256;

	@FXML
	private TextField TextFieldSHA2_384;

	@FXML
	private TextField TextFieldSHA2_512;

	@FXML
	private TextField TextFieldSHA3_224;

	@FXML
	private TextField TextFieldSHA3_256;

	@FXML
	private TextField TextFieldSHA3_384;

	@FXML
	private TextField TextFieldSHA3_512;

	@FXML
	private Button ButtonMD5;

	@FXML
	private Button ButtonSHA1;

	@FXML
	private Button ButtonSHA2_224;

	@FXML
	private Button ButtonSHA2_256;

	@FXML
	private Button ButtonSHA2_384;

	@FXML
	private Button ButtonSHA2_512;

	@FXML
	private Button ButtonSHA3_224;

	@FXML
	private Button ButtonSHA3_256;

	@FXML
	private Button ButtonSHA3_384;

	@FXML
	private Button ButtonSHA3_512;

	@FXML
	private Label LabelStatus;

	@FXML
	private TextField TextFieldVerifyChecksum;

	@FXML
	private Button ButtonVerifyChecksum;

	@FXML
	private Label LabelVerifyChecksum;

	@FXML
	void onDragOverEvent(DragEvent event) {
		if (event.getGestureSource() != this.anchorPane && event.getDragboard().hasFiles()) {
			event.acceptTransferModes(TransferMode.COPY);
		}
		event.consume();
	}

	@FXML
	void onDragDroppedEvent(DragEvent event) {
		Dragboard dragboard = event.getDragboard();
		if (dragboard.hasFiles()) {
			String fileName = dragboard.getFiles().get(0).getAbsolutePath();
			this.file = new File(fileName);
			this.TextFieldFile.setText(this.file.getAbsolutePath());
			this.enableButtons();
			this.clearFields();
			this.TextFieldVerifyChecksum.setEditable(true);
		}
		event.setDropCompleted(true);
		event.consume();
	}

	@FXML
	void openFile(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choose A File To Compute Hash Values For It!");
		this.file = fileChooser.showOpenDialog(null);
		if (this.file != null) {
			this.TextFieldFile.setText(this.file.getAbsolutePath());
			this.enableButtons();
			this.clearFields();
			this.TextFieldVerifyChecksum.setEditable(true);
		} else {
			this.disableButtons();
			this.TextFieldFile.setText(null);
			this.clearFields();
			this.TextFieldVerifyChecksum.setEditable(false);
		}
	}

	@FXML
	void hashMD5(ActionEvent event) {
		HashFile md5Task = new HashFile(this.getFile(), HashAlgorithms.getMD5());
		md5Task.setOnRunning(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				taskRunning(ButtonMD5);
			}
		});
		md5Task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				taskSucceded(TextFieldMD5, md5Task.getValue());
			}
		});
		md5Task.setOnFailed(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				taskFailure(ButtonMD5, event.getSource().getException().getMessage());
				clearFields();
			}
		});
		Thread thread = new Thread(md5Task);
		thread.start();
	}

	@FXML
	void hashSHA1(ActionEvent event) {
		HashFile sha1Task = new HashFile(this.getFile(), HashAlgorithms.getSHA1());
		sha1Task.setOnRunning(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				taskRunning(ButtonSHA1);
			}
		});
		sha1Task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				taskSucceded(TextFieldSHA1, sha1Task.getValue());
			}
		});
		sha1Task.setOnFailed(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				taskFailure(ButtonSHA1, event.getSource().getException().getMessage());
				clearFields();
			}
		});
		Thread thread = new Thread(sha1Task);
		thread.start();
	}

	@FXML
	void hashSHA2_224(ActionEvent event) {
		HashFile sha2_224Task = new HashFile(this.getFile(), HashAlgorithms.getSHA2_224());
		sha2_224Task.setOnRunning(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				taskRunning(ButtonSHA2_224);
			}
		});
		sha2_224Task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				taskSucceded(TextFieldSHA2_224, sha2_224Task.getValue());
			}
		});
		sha2_224Task.setOnFailed(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				taskFailure(ButtonSHA2_224, event.getSource().getException().getMessage());
				clearFields();
			}
		});
		Thread thread = new Thread(sha2_224Task);
		thread.start();
	}

	@FXML
	void hashSHA2_256(ActionEvent event) {
		HashFile sha2_256Task = new HashFile(this.getFile(), HashAlgorithms.getSHA2_256());
		sha2_256Task.setOnRunning(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				taskRunning(ButtonSHA2_256);
			}
		});
		sha2_256Task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				taskSucceded(TextFieldSHA2_256, sha2_256Task.getValue());
			}
		});
		sha2_256Task.setOnFailed(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				taskFailure(ButtonSHA2_256, event.getSource().getException().getMessage());
				clearFields();
			}
		});
		Thread thread = new Thread(sha2_256Task);
		thread.start();
	}

	@FXML
	void hashSHA2_384(ActionEvent event) {
		HashFile sha2_384Task = new HashFile(this.getFile(), HashAlgorithms.getSHA2_384());
		sha2_384Task.setOnRunning(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				taskRunning(ButtonSHA2_384);
			}
		});
		sha2_384Task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				taskSucceded(TextFieldSHA2_384, sha2_384Task.getValue());
			}
		});
		sha2_384Task.setOnFailed(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				taskFailure(ButtonSHA2_384, event.getSource().getException().getMessage());
				clearFields();
			}
		});
		Thread thread = new Thread(sha2_384Task);
		thread.start();
	}

	@FXML
	void hashSHA2_512(ActionEvent event) {
		HashFile sha2_512Task = new HashFile(this.getFile(), HashAlgorithms.getSHA2_512());
		sha2_512Task.setOnRunning(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				taskRunning(ButtonSHA2_512);
			}
		});
		sha2_512Task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				taskSucceded(TextFieldSHA2_512, sha2_512Task.getValue());
			}
		});
		sha2_512Task.setOnFailed(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				taskFailure(ButtonSHA2_512, event.getSource().getException().getMessage());
				clearFields();
			}
		});
		Thread thread = new Thread(sha2_512Task);
		thread.start();
	}

	@FXML
	void hashSHA3_224(ActionEvent event) {
		HashFile sha3_224Task = new HashFile(this.getFile(), HashAlgorithms.getSHA3_224());
		sha3_224Task.setOnRunning(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				taskRunning(ButtonSHA3_224);
			}
		});
		sha3_224Task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				taskSucceded(TextFieldSHA3_224, sha3_224Task.getValue());
			}
		});
		sha3_224Task.setOnFailed(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				taskFailure(ButtonSHA3_224, event.getSource().getException().getMessage());
				clearFields();
			}
		});
		Thread thread = new Thread(sha3_224Task);
		thread.start();
	}

	@FXML
	void hashSHA3_256(ActionEvent event) {
		HashFile sha3_256Task = new HashFile(this.getFile(), HashAlgorithms.getSHA3_256());
		sha3_256Task.setOnRunning(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				taskRunning(ButtonSHA3_256);
			}
		});
		sha3_256Task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				taskSucceded(TextFieldSHA3_256, sha3_256Task.getValue());
			}
		});
		sha3_256Task.setOnFailed(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				taskFailure(ButtonSHA3_256, event.getSource().getException().getMessage());
				clearFields();
			}
		});
		Thread thread = new Thread(sha3_256Task);
		thread.start();
	}

	@FXML
	void hashSHA3_384(ActionEvent event) {
		HashFile sha3_384Task = new HashFile(this.getFile(), HashAlgorithms.getSHA3_384());
		sha3_384Task.setOnRunning(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				taskRunning(ButtonSHA3_384);
			}
		});
		sha3_384Task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				taskSucceded(TextFieldSHA3_384, sha3_384Task.getValue());
			}
		});
		sha3_384Task.setOnFailed(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				taskFailure(ButtonSHA3_384, event.getSource().getException().getMessage());
				clearFields();
			}
		});
		Thread thread = new Thread(sha3_384Task);
		thread.start();
	}

	@FXML
	void hashSHA3_512(ActionEvent event) {
		HashFile sha3_512Task = new HashFile(this.getFile(), HashAlgorithms.getSHA3_512());
		sha3_512Task.setOnRunning(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				taskRunning(ButtonSHA3_512);
			}
		});
		sha3_512Task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				taskSucceded(TextFieldSHA3_512, sha3_512Task.getValue());
			}
		});
		sha3_512Task.setOnFailed(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				taskFailure(ButtonSHA3_512, event.getSource().getException().getMessage());
				clearFields();
			}
		});
		Thread thread = new Thread(sha3_512Task);
		thread.start();
	}

	@FXML
	void compareHash(ActionEvent event) {
		List<String> result = compareHashValues(this.TextFieldVerifyChecksum.getText());
		if (result.size() == 2) {
			this.displayAlert(AlertType.INFORMATION, result.get(0) + result.get(1), ButtonType.OK);
		} else {
			this.displayAlert(AlertType.WARNING, result.get(0), ButtonType.OK);
		}
	}

	@FXML
	void initialize() {
		assert LabelFile != null : "fx:id=\"LabelFile\" was not injected: check your FXML file 'MainForm.fxml'.";
		assert TextFieldFile != null : "fx:id=\"TextFieldFile\" was not injected: check your FXML file 'MainForm.fxml'.";
		assert ButtonOpenFile != null : "fx:id=\"ButtonOpenFile\" was not injected: check your FXML file 'MainForm.fxml'.";
		assert TextFieldMD5 != null : "fx:id=\"TextFieldMD5\" was not injected: check your FXML file 'MainForm.fxml'.";
		assert TextFieldSHA1 != null : "fx:id=\"TextFieldSHA1\" was not injected: check your FXML file 'MainForm.fxml'.";
		assert TextFieldSHA2_224 != null : "fx:id=\"TextFieldSHA2_224\" was not injected: check your FXML file 'MainForm.fxml'.";
		assert TextFieldSHA2_256 != null : "fx:id=\"TextFieldSHA2_256\" was not injected: check your FXML file 'MainForm.fxml'.";
		assert TextFieldSHA2_384 != null : "fx:id=\"TextFieldSHA2_384\" was not injected: check your FXML file 'MainForm.fxml'.";
		assert TextFieldSHA2_512 != null : "fx:id=\"TextFieldSHA2_512\" was not injected: check your FXML file 'MainForm.fxml'.";
		assert TextFieldSHA3_224 != null : "fx:id=\"TextFieldSHA3_224\" was not injected: check your FXML file 'MainForm.fxml'.";
		assert TextFieldSHA3_256 != null : "fx:id=\"TextFieldSHA3_256\" was not injected: check your FXML file 'MainForm.fxml'.";
		assert TextFieldSHA3_384 != null : "fx:id=\"TextFieldSHA3_384\" was not injected: check your FXML file 'MainForm.fxml'.";
		assert TextFieldSHA3_512 != null : "fx:id=\"TextFieldSHA3_512\" was not injected: check your FXML file 'MainForm.fxml'.";
		assert ButtonMD5 != null : "fx:id=\"ButtonMD5\" was not injected: check your FXML file 'MainForm.fxml'.";
		assert ButtonSHA1 != null : "fx:id=\"ButtonSHA1\" was not injected: check your FXML file 'MainForm.fxml'.";
		assert ButtonSHA2_224 != null : "fx:id=\"ButtonSHA2_224\" was not injected: check your FXML file 'MainForm.fxml'.";
		assert ButtonSHA2_256 != null : "fx:id=\"ButtonSHA2_256\" was not injected: check your FXML file 'MainForm.fxml'.";
		assert ButtonSHA2_384 != null : "fx:id=\"ButtonSHA2_384\" was not injected: check your FXML file 'MainForm.fxml'.";
		assert ButtonSHA2_512 != null : "fx:id=\"ButtonSHA2_512\" was not injected: check your FXML file 'MainForm.fxml'.";
		assert ButtonSHA3_224 != null : "fx:id=\"ButtonSHA3_224\" was not injected: check your FXML file 'MainForm.fxml'.";
		assert ButtonSHA3_256 != null : "fx:id=\"ButtonSHA3_256\" was not injected: check your FXML file 'MainForm.fxml'.";
		assert ButtonSHA3_384 != null : "fx:id=\"ButtonSHA3_384\" was not injected: check your FXML file 'MainForm.fxml'.";
		assert ButtonSHA3_512 != null : "fx:id=\"ButtonSHA3_512\" was not injected: check your FXML file 'MainForm.fxml'.";
		assert LabelStatus != null : "fx:id=\"LabelStatus\" was not injected: check your FXML file 'MainForm.fxml'.";
		assert TextFieldVerifyChecksum != null : "fx:id=\"TextFieldVerifyChecksum\" was not injected: check your FXML file 'MainForm.fxml'.";
		assert ButtonVerifyChecksum != null : "fx:id=\"ButtonVerifyChecksum\" was not injected: check your FXML file 'MainForm.fxml'.";
		assert LabelVerifyChecksum != null : "fx:id=\"LabelVerifyChecksum\" was not injected: check your FXML file 'MainForm.fxml'.";
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		this.disableButtons();
		this.setTextFieldPropeties();
		this.TextFieldVerifyChecksum.setEditable(false);
	}

	private void taskRunning(Button button) {
		this.ButtonOpenFile.setDisable(true);
		button.setDisable(true);
	}

	private void taskFailure(Button button, String exceptionMessage) {
		button.setDisable(false);
		this.displayAlert(AlertType.ERROR, exceptionMessage, ButtonType.OK);
		this.clearFields();
	}

	private void taskSucceded(TextField textField, String value) {
		textField.setText(value);
		this.ButtonOpenFile.setDisable(false);
	}

	private void clearFields() {
		this.TextFieldMD5.setText(null);
		this.TextFieldSHA1.setText(null);
		this.TextFieldSHA2_224.setText(null);
		this.TextFieldSHA2_256.setText(null);
		this.TextFieldSHA2_384.setText(null);
		this.TextFieldSHA2_512.setText(null);
		this.TextFieldSHA3_224.setText(null);
		this.TextFieldSHA3_256.setText(null);
		this.TextFieldSHA3_384.setText(null);
		this.TextFieldSHA3_512.setText(null);
		this.TextFieldVerifyChecksum.setText(null);
	}

	private void disableButtons() {
		this.ButtonMD5.setDisable(true);
		this.ButtonSHA1.setDisable(true);
		this.ButtonSHA2_224.setDisable(true);
		this.ButtonSHA2_256.setDisable(true);
		this.ButtonSHA2_384.setDisable(true);
		this.ButtonSHA2_512.setDisable(true);
		this.ButtonSHA3_224.setDisable(true);
		this.ButtonSHA3_256.setDisable(true);
		this.ButtonSHA3_384.setDisable(true);
		this.ButtonSHA3_512.setDisable(true);
		this.ButtonVerifyChecksum.setDisable(true);
	}

	private void enableButtons() {
		this.ButtonMD5.setDisable(false);
		this.ButtonSHA1.setDisable(false);
		this.ButtonSHA2_224.setDisable(false);
		this.ButtonSHA2_256.setDisable(false);
		this.ButtonSHA2_384.setDisable(false);
		this.ButtonSHA2_512.setDisable(false);
		this.ButtonSHA3_224.setDisable(false);
		this.ButtonSHA3_256.setDisable(false);
		this.ButtonSHA3_384.setDisable(false);
		this.ButtonSHA3_512.setDisable(false);
		this.ButtonVerifyChecksum.setDisable(false);
	}

	private void setTextFieldPropeties() {
		this.TextFieldFile.setEditable(false);
		this.TextFieldMD5.setEditable(false);
		this.TextFieldSHA1.setEditable(false);
		this.TextFieldSHA2_224.setEditable(false);
		this.TextFieldSHA2_256.setEditable(false);
		this.TextFieldSHA2_384.setEditable(false);
		this.TextFieldSHA2_512.setEditable(false);
		this.TextFieldSHA3_224.setEditable(false);
		this.TextFieldSHA3_256.setEditable(false);
		this.TextFieldSHA3_384.setEditable(false);
		this.TextFieldSHA3_512.setEditable(false);
	}

	private List<String> compareHashValues(String hashValue) {
		List<String> results = new ArrayList<String>();
		if (hashValue != null) {
			if (this.TextFieldMD5.getText() != null
					&& this.TextFieldMD5.getText().toLowerCase().equals(hashValue.toLowerCase())) {
				results.add("MD5s Are ");
				results.add("Equal");
				return results;
			} else if (this.TextFieldSHA1.getText() != null
					&& this.TextFieldSHA1.getText().toLowerCase().equals(hashValue.toLowerCase())) {
				results.add("SHA1s Are ");
				results.add("Equal");
				return results;
			} else if (this.TextFieldSHA2_224.getText() != null
					&& this.TextFieldSHA2_224.getText().toLowerCase().equals(hashValue.toLowerCase())) {
				results.add("SHA2-224s Are ");
				results.add("Equal");
				return results;
			} else if (this.TextFieldSHA2_256.getText() != null
					&& this.TextFieldSHA2_256.getText().toLowerCase().equals(hashValue.toLowerCase())) {
				results.add("SHA2-256s Are ");
				results.add("Equal");
				return results;
			} else if (this.TextFieldSHA2_384.getText() != null
					&& this.TextFieldSHA2_384.getText().toLowerCase().equals(hashValue.toLowerCase())) {
				results.add("SHA2-384s Are ");
				results.add("Equal");
				return results;
			} else if (this.TextFieldSHA2_512.getText() != null
					&& this.TextFieldSHA2_512.getText().toLowerCase().equals(hashValue.toLowerCase())) {
				results.add("SHA2-512s Are ");
				results.add("Equal");
				return results;
			} else if (this.TextFieldSHA3_224.getText() != null
					&& this.TextFieldSHA3_224.getText().toLowerCase().equals(hashValue.toLowerCase())) {
				results.add("SHA3-224s Are ");
				results.add("Equal");
				return results;
			} else if (this.TextFieldSHA3_256.getText() != null
					&& this.TextFieldSHA3_256.getText().toLowerCase().equals(hashValue.toLowerCase())) {
				results.add("SHA3-256s Are ");
				results.add("Equal");
				return results;
			} else if (this.TextFieldSHA3_384.getText() != null
					&& this.TextFieldSHA3_384.getText().toLowerCase().equals(hashValue.toLowerCase())) {
				results.add("SHA3-384s Are ");
				results.add("Equal");
				return results;
			} else if (this.TextFieldSHA3_512.getText() != null
					&& this.TextFieldSHA3_512.getText().toLowerCase().equals(hashValue.toLowerCase())) {
				results.add("SHA3-512s Are ");
				results.add("Equal");
				return results;
			} else if (this.TextFieldVerifyChecksum.getText() != null
					&& this.TextFieldVerifyChecksum.getText().equals(null)) {
				results.add("Hashes Not Equal ");
				return results;
			} else {
				results.add("Hashes Not Equal ");
				return results;
			}
		} else {
			results.add("Hashes Not Equal ");
			return results;
		}
	}

	private void displayAlert(AlertType alertType, String alertMessage, ButtonType buttonType) {
		Alert alert = new Alert(alertType, alertMessage, buttonType);
		alert.show();
	}
}