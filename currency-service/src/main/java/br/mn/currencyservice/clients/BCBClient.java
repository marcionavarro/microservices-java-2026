package br.mn.currencyservice.clients;

import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "bcb-client", url = "https://olinda.bcb.gov.br/olinda/servico/PTAX/versao/v1/odata")
public interface BCBClient {
    // consulta Data fixa 19/06/2026
    @GetMapping("CotacaoMoedaDia(moeda=@moeda,dataCotacao=@dataCotacao)?%40moeda='{moeda}'&%40dataCotacao=%2706-19-2026%27&%24format=json")
    @Retry(name = "bcb-client")
    BCBResponse getBCBCurrency(@PathVariable String moeda);
}
