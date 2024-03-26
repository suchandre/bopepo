/*
 * Copyright 2014 JRimum Project
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * Created at: 19/01/2014 - 22:11:18
 * ================================================================================
 * Direitos autorais 2014 JRimum Project
 * Licenciado sob a Licença Apache, Versão 2.0 ("LICENÇA"); você não pode
 * usar esse arquivo exceto em conformidade com a esta LICENÇA. Você pode obter uma
 * cópia desta LICENÇA em http://www.apache.org/licenses/LICENSE-2.0 A menos que
 * haja exigência legal ou acordo por escrito, a distribuição de software sob esta
 * LICENÇA se dará “COMO ESTÁ”, SEM GARANTIAS OU CONDIÇÕES DE QUALQUER TIPO, sejam
 * expressas ou tácitas. Veja a LICENÇA para a redação específica a reger permissões
 * e limitações sob esta LICENÇA.
 * Criado em: 19/01/2014 - 22:11:18
 */

package org.jrimum.bopepo.view.info.campo;

import static org.jrimum.bopepo.view.info.campo.BoletoInfoCampoAgenciaCodigoCedente.getTextoAgenciaCodigoCedente;
import static org.jrimum.bopepo.view.info.campo.BoletoInfoCampoCodigoBanco.getTextoCodigoDoBanco;
import static org.jrimum.bopepo.view.info.campo.BoletoInfoCampoEndereco.getTextoEnderecoLinha1;
import static org.jrimum.bopepo.view.info.campo.BoletoInfoCampoEndereco.getTextoEnderecoLinha2;
import static org.jrimum.bopepo.view.info.campo.BoletoInfoCampoLogoBanco.getImagemBanco;
import static org.jrimum.bopepo.view.info.campo.BoletoInfoCampoNossoNumero.getTextoNossoNumero;
import static org.jrimum.bopepo.view.info.campo.BoletoInfoCampoPessoa.getTextoNomeCprfDaPessoa;
import static org.jrimum.utilix.Objects.isNotNull;
import static org.jrimum.utilix.Objects.whenNull;
import static org.jrimum.utilix.text.DateFormat.DDMMYYYY_B;
import static org.jrimum.utilix.text.DecimalFormat.MONEY_DD_BR;

import java.awt.Image;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.jrimum.bopepo.Boleto;
import org.jrimum.bopepo.pdf.CodigoDeBarras;
import org.jrimum.bopepo.view.ResourceBundle;
import org.jrimum.utilix.Exceptions;
import org.jrimum.utilix.Objects;

/**
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L.</a>
 *
 */
public abstract class AbstractBoletoInfoCampoView implements BoletoInfoCampoView {

	private final ResourceBundle resourceBundle;

	private final Boleto boleto;

	/**
	 * Modo de instanciação não permitido.
	 * 
	 * @throws IllegalStateException
	 *             Caso haja alguma tentativa de utilização deste construtor.
	 */
	@SuppressWarnings("unused")
	private AbstractBoletoInfoCampoView() {
		Exceptions.throwIllegalStateException("Instanciação não permitida!");
		resourceBundle = null;
		boleto = null;
	}

	public AbstractBoletoInfoCampoView(ResourceBundle resourceBundle, Boleto boleto) {
		Objects.checkNotNull(resourceBundle);
		Objects.checkNotNull(boleto);
		this.resourceBundle = resourceBundle;
		this.boleto = boleto;
	}

	@Override
	public String getTextoRsInstrucaoAoSacado() {
		return getValue(boleto.getInstrucaoAoSacado());
	}

	@Override
	public String getTextoRsCpfCnpj() {
		return boleto.getTitulo().getCedente().getCPRF().getCodigoFormatado();
	}

	@Override
	public String getTextoRsSacado() {
		return getTextoFcSacadoL1();
	}

	@Override
	public String getTextoFcSacadoL1() {
		return getTextoNomeCprfDaPessoa(boleto.getTitulo().getSacado());
	}

	@Override
	public String getTextoFcSacadoL2() {
		return getTextoEnderecoLinha1(boleto.getTitulo().getSacado());
	}

	@Override
	public String getTextoFcSacadoL3() {
		return getTextoEnderecoLinha2(boleto.getTitulo().getSacado());
	}

	@Override
	public String getTextoFcSacadorAvalistaL1() {
		return getTextoNomeCprfDaPessoa(boleto.getTitulo().getSacadorAvalista());
	}

	@Override
	public String getTextoFcSacadorAvalistaL2() {
		return getTextoEnderecoLinha1(boleto.getTitulo().getSacadorAvalista());
	}

	@Override
	public String getTextoFcSacadorAvalistaL3() {
		return getTextoEnderecoLinha2(boleto.getTitulo().getSacadorAvalista());
	}

	@Override
	public String getTextoFcDataProcessamento() {
		return getValue(boleto.getDataDeProcessamento());
	}

	@Override
	public String getTextoFcAceite() {
		return getValue(boleto.getTitulo().getAceite());
	}

	@Override
	public String getTextoFcEspecieDocumento() {
		return whenNull(boleto.getTitulo().getTipoDeDocumento(), StringUtils.EMPTY, boleto.getTitulo().getTipoDeDocumento().getSigla());
	}

	@Override
	public String getTextoFcDataDocumento() {
		return getValue(boleto.getTitulo().getDataDoDocumento());
	}

	@Override
	public String getTextoFcLocalPagamento() {
		return getValue(boleto.getLocalPagamento());
	}

	@Override
	public String getTextoFcCarteira() {
		return whenNull(boleto.getTitulo().getContaBancaria().getCarteira(), StringUtils.EMPTY, boleto.getTitulo().getContaBancaria().getCarteira().getCodigo().toString());
	}

	@Override
	public String getTextoFcInstrucaoAoCaixa1() {
		return getValue(boleto.getInstrucao1());
	}

	@Override
	public String getTextoFcInstrucaoAoCaixa2() {
		return getValue(boleto.getInstrucao2());
	}

	@Override
	public String getTextoFcInstrucaoAoCaixa3() {
		return getValue(boleto.getInstrucao3());
	}

	@Override
	public String getTextoFcInstrucaoAoCaixa4() {
		return getValue(boleto.getInstrucao4());
	}

	@Override
	public String getTextoFcInstrucaoAoCaixa5() {
		return getValue(boleto.getInstrucao5());
	}

	@Override
	public String getTextoFcInstrucaoAoCaixa6() {
		return getValue(boleto.getInstrucao6());
	}

	@Override
	public String getTextoFcInstrucaoAoCaixa7() {
		return getValue(boleto.getInstrucao7());
	}

	@Override
	public String getTextoFcInstrucaoAoCaixa8() {
		return getValue(boleto.getInstrucao8());
	}

	@Override
	public String getTextoRsMoraMulta() {
		return getTextoFcMoraMulta();
	}

	@Override
	public String getTextoFcMoraMulta() {
		return getValue(boleto.getTitulo().getMora());
	}

	@Override
	public String getTextoRsOutroAcrescimo() {
		return getTextoFcOutroAcrescimo();
	}

	@Override
	public String getTextoFcOutroAcrescimo() {
		return getValue(boleto.getTitulo().getAcrecimo());
	}

	@Override
	public String getTextoRsOutraDeducao() {
		return getTextoFcOutraDeducao();
	}

	@Override
	public String getTextoFcOutraDeducao() {
		return getValue(boleto.getTitulo().getDeducao());
	}

	@Override
	public String getTextoRsDescontoAbatimento() {
		return getTextoFcDescontoAbatimento();
	}

	@Override
	public String getTextoFcDescontoAbatimento() {
		return getValue(boleto.getTitulo().getDesconto());
	}

	@Override
	public String getTextoRsValorDocumento() {
		return getTextoFcValorDocumento();
	}

	@Override
	public String getTextoFcValorDocumento() {
		return getValue(boleto.getTitulo().getValor());
	}

	@Override
	public String getTextoRsValorCobrado() {
		return getTextoFcValorCobrado();
	}

	@Override
	public String getTextoFcValorCobrado() {
		return getValue(boleto.getTitulo().getValorCobrado());
	}

	@Override
	public String getTextoRsDataVencimento() {
		return getTextoFcDataVencimento();
	}

	@Override
	public String getTextoFcDataVencimento() {
		return getValue(boleto.getTitulo().getDataDoVencimento());
	}

	@Override
	public String getTextoRsNumeroDocumento() {
		return getTextoFcNumeroDocumento();
	}

	@Override
	public String getTextoFcNumeroDocumento() {
		return getValue(boleto.getTitulo().getNumeroDoDocumento());
	}

	@Override
	public String getTextoRsCedente() {
		return getTextoFcCedente();
	}

	@Override
	public String getTextoFcCedente() {
		return getValue(boleto.getTitulo().getCedente().getNome());
	}

	@Override
	public String getTextoRsEspecie() {
		return getTextoFcEspecie();
	}

	@Override
	public String getTextoFcEspecie() {
		return getValue(boleto.getTitulo().getTipoDeMoeda());
	}

	@Override
	public String getTextoRsCodigoBanco() {
		return getTextoFcCodigoBanco();
	}

	@Override
	public String getTextoFcCodigoBanco() {
		return getTextoCodigoDoBanco(boleto.getTitulo().getContaBancaria());
	}

	@Override
	public String getTextoRsAgenciaCodigoCedente() {
		return getTextoFcAgenciaCodigoCedente();
	}

	@Override
	public String getTextoFcAgenciaCodigoCedente() {
		return getTextoAgenciaCodigoCedente(boleto.getTitulo().getContaBancaria());
	}

	@Override
	public String getTextoRsNossoNumero() {
		return getTextoFcNossoNumero();
	}

	@Override
	public String getTextoFcNossoNumero() {
		return getTextoNossoNumero(boleto.getTitulo());
	}

	@Override
	public Image getImagemRsLogoBanco() {
		return getImagemFcLogoBanco();
	}

	@Override
	public Image getImagemFcLogoBanco() {
		return getImagemBanco(resourceBundle, boleto.getTitulo().getContaBancaria());
	}

	@Override
	public String getTextoRsLogoBanco() {
		return getTextoFcLogoBanco();
	}

	@Override
	public String getTextoFcLogoBanco() {
		return boleto.getTitulo().getContaBancaria().getBanco().getNome();
	}

	@Override
	public String getTextoRsLinhaDigitavel() {
		return getTextoFcLinhaDigitavel();
	}

	@Override
	public String getTextoFcLinhaDigitavel() {
		return boleto.getLinhaDigitavel().write();
	}

	@Override
	public Image getImagemFcCodigoBarra() {
		return CodigoDeBarras.valueOf(boleto.getCodigoDeBarras().write()).toImage();
	}

	protected final Boleto getBoleto() {
		return this.boleto;
	}

	private String getValue(String value) {
		if (StringUtils.isNotBlank(value)) {
			return value;
		}
		return StringUtils.EMPTY;
	}

	private String getValue(Date value) {
		if (isNotNull(value)) {
			return DDMMYYYY_B.format(value);
		}
		return StringUtils.EMPTY;
	}

	private String getValue(BigDecimal value) {
		if (isNotNull(value)) {
			return MONEY_DD_BR.format(value);
		}
		return StringUtils.EMPTY;
	}

	private <T extends Enum<?>> String getValue(T value) {
		if (isNotNull(value)) {
			return value.name();
		}
		return StringUtils.EMPTY;
	}
}
