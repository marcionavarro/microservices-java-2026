package br.mn.currencyservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "bcb-client", url = "https://olinda.bcb.gov.br/olinda/servico/PTAX/versao/v1/odata")
public interface BCBClient {
    // consulta Data fixa 19/06/2026
    @GetMapping("CotacaoMoedaDia(moeda=@moeda,dataCotacao=@dataCotacao)?%40moeda='{moeda}'&%40dataCotacao=%2706-19-2026%27&%24format=json")
    BCBResponse getBCBCurrency(@PathVariable String moeda);
}
