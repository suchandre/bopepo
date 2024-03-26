package org.jrimum.bopepo.view.info.campo.hsbc;

import static org.junit.Assert.assertEquals;

import org.apache.commons.lang3.StringUtils;
import org.jrimum.bopepo.Boleto;
import org.jrimum.bopepo.excludes.BoletoBuilder;
import org.jrimum.bopepo.view.ResourceBundle;
import org.jrimum.domkee.financeiro.banco.febraban.Agencia;
import org.jrimum.domkee.financeiro.banco.febraban.NumeroDaConta;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author Rômulo Augusto
 */
public class TestBoletoInfoViewHSBCNaoRegistrada {

	private BoletoInfoViewHSBCNaoRegistrada view;
	private Boleto boleto;

	@Before
	public void setUp() {
		boleto = BoletoBuilder.defaultValue();
		view = new BoletoInfoViewHSBCNaoRegistrada(Mockito.mock(ResourceBundle.class), boleto);
	}

	@Test
	public void deve_retornar_local_de_pagamento_padrao() {
		boleto.setLocalPagamento(null);
		assertEquals("PAGAR PREFERENCIALMENTE EM AGÊNCIA DO HSBC", view.getTextoFcLocalPagamento());
	}

	@Test
	public void deve_retornar_apenas_codigo_do_cliente_no_campo_agencia_codigo_cliente() {
		boleto.getTitulo().getContaBancaria().setAgencia(new Agencia(1234));
		boleto.getTitulo().getContaBancaria().setNumeroDaConta(new NumeroDaConta(1234567, "0"));

		assertEquals("1234567", view.getTextoFcAgenciaCodigoCedente());
		assertEquals("1234567", view.getTextoRsAgenciaCodigoCedente());
	}

	@Test
	public void deve_retornar_especie_documento_em_branco() {
		assertEquals(StringUtils.EMPTY, view.getTextoFcEspecieDocumento());
	}

	@Test
	public void deve_retornar_aceite_em_branco() {
		assertEquals(StringUtils.EMPTY, view.getTextoFcAceite());
	}

	@Test
	public void deve_retornar_nosso_numero_e_dv_sem_tracos() {
		boleto.getTitulo().setNossoNumero("0000000123456");
		boleto.getTitulo().setDigitoDoNossoNumero("941");

		assertEquals("0000000123456941", view.getTextoFcNossoNumero());
		assertEquals("0000000123456941", view.getTextoRsNossoNumero());
	}

	@Test
	public void deve_retornar_carteira_constante() {
		assertEquals("CNR", view.getTextoFcCarteira());
	}
}
