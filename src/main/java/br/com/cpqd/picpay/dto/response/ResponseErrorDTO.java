package br.com.cpqd.picpay.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseErrorDTO {

	public Instant timestamp;

	public Integer status;

	public String error;

	public String message;

	public String path;

}
