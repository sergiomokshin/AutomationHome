package automacaolivre.automationhomeethernet;

public class Saida {
    private String _Codigo;
    private String _Nome;
    private String _HorarioInicio;
    private String _HorarioFim;

    public Saida() {

    }

    public Saida(String Codigo, String Nome, String HorarioInicio, String HorarioFim) {
        this._Codigo = Codigo;
        this._Nome = Nome;
        this._HorarioInicio = HorarioInicio;
        this._HorarioFim = HorarioFim;
    }

    public String getCodigo() {
        return _Codigo;
    }

    public void setCodigo(String Codigo) {
        this._Codigo = Codigo;
    }

    public String getNome() {
        return _Nome;
    }

    public void setNome(String Nome) {
        this._Nome = Nome;
    }

    public String getHorarioInicio() {
        return _HorarioInicio;
    }

    public void setHorarioInicio(String HorarioInicio) {
        this._HorarioInicio = HorarioInicio;
    }

    public String getHorarioFim() {
        return _HorarioFim;
    }

    public void setHorarioFim(String HorarioFim) {
        this._HorarioFim = HorarioFim;
    }


}