import { Component } from 'react';
import Combobox from '@salesforce/design-system-react/components/combobox';
import Input from '@salesforce/design-system-react/components/input';

import { LIST_OPTIONS } from 'constants/semester';

export default class EarningsForm extends Component {
  state = {
    item: {
      art: '',
      betrag: 0,
      semester: LIST_OPTIONS[LIST_OPTIONS.length - 1].id
    }
  };

  get semesterLabel() {
    return LIST_OPTIONS.find(option => option.id === this.state.item.semester)?.label;
  }

  componentDidMount() {
    if (this.props.presetEarning) {
      this.setState({ item: { ...this.props.presetEarning } });
    }
  }

  addValueToItem = (key, value) => {
    this.setState(state => ({ item: { ...state.item, [key]: value } }));
  };

  handleKindChange = (event, { value }) => {
    this.addValueToItem('art', value);
  };

  handleValueChange = (event, { value }) => {
    this.addValueToItem('betrag', value ? +value : '');
  };

  handleComboboxSelect = (_, { selection }) => {
    const [selectedItem] = selection;
    this.addValueToItem('semester', selectedItem.id);
  };

  render = () => (
    <section className="slds-var-p-around_small slds-grid slds-grid_pull-padded slds-wrap">
      <div className="slds-col slds-col_padded slds-size_full slds-form-element slds-var-m-bottom_small">
        <Input
          id="art"
          label="Beschreibung"
          placeholder="Spieleinnahmen Peter (yyyy-mm-dd)"
          type="text"
          required
          value={this.state.item.art}
          onChange={this.handleKindChange}
        />
      </div>

      <div className="slds-col slds-col_padded  slds-size_full slds-large-size_1-of-2 slds-form-element slds-var-m-bottom_small">
        <Input
          id="betrag"
          label="Betrag"
          type="number"
          fixedTextLeft="€"
          step={0.1}
          required
          value={this.state.item.betrag}
          onChange={this.handleValueChange}
        />
      </div>

      <div className="slds-col slds-col_padded slds-size_full slds-large-size_1-of-2 slds-form-element slds-var-m-bottom_small">
        <Combobox
          labels={{ label: 'Semester', placeholder: 'Semester auswählen' }}
          options={LIST_OPTIONS}
          required
          value={this.semesterLabel}
          events={{ onSelect: this.handleComboboxSelect }}
          id="semester"
        />
      </div>
    </section>
  );
}
